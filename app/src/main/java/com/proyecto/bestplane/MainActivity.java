package com.proyecto.bestplane;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Residuo> preguntas = new ArrayList<>();
    private static final String TAG = "CrearCuentaActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ImageView imageView;
    private Button button;
    private RadioGroup radioGroup;
    private ProgressBar progressBar;
    private RadioButton radioButton;
    private int indice = 0;
    private int respuestasCorrectas=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("Test");


        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imagen);
        button = findViewById(R.id.boton);
        radioGroup = findViewById(R.id.radioGroup);
        progressBar = findViewById(R.id.progreso);
        progressBar.setMax(100);

        guardarPreguntas();
        cambiarPregunta(indice);
        //inicia firBase
        initialize();

        //determina si se hizo click en el RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //cambia de color el boton si se selecciono una opcion
                button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                      // si un radio boton es seleccionado pasa a la siguiente pregunta
                     if(radioGroup.getCheckedRadioButtonId()!=-1){
                         if(indice < preguntas.size()){
                             comparaRespuesta(indice);
                             indice++;
                             int p = indice;
                             if (p<preguntas.size()){
                                 cambiarPregunta(indice);
                             }else {
                                 // guarda informacion del usuario
                                 double puntaje = (double) 5*respuestasCorrectas/preguntas.size();
                                 String nombre = getIntent().getStringExtra("nombnre");
                                 String cod = getIntent().getStringExtra("codigo");
                                 // envia informacion del usuario a la base de datos
                                 cuentanueva(nombre, cod, puntaje);
                                 Intent intent = new Intent(MainActivity.this, Calificacion.class);
                                 intent.putExtra("rCorrectas", ""+ puntaje);
                                 startActivity(intent);
                             }
                         }
                         //cambia color del boton
                         button.setBackgroundColor(getResources().getColor(R.color.colorBoton));
                     }
                        }
                    });
            }
        });

    }

    private void guardarPreguntas(){

        preguntas.add(new Residuo("Papel", "Reciclable",R.drawable.papel));
        preguntas.add(new Residuo("Baterias", "Peligroso",R.drawable.bat));
        preguntas.add(new Residuo("Jeringa", "Peligroso",R.drawable.jeringa));
        preguntas.add(new Residuo("Vasos", "Reciclable",R.drawable.vasos));
        preguntas.add(new Residuo("Vidrio", "Reciclable",R.drawable.vidrio));
        preguntas.add(new Residuo("Cascaras", "Organico",R.drawable.cascaras));
        preguntas.add(new Residuo("Monitores", "Tecnologico",R.drawable.tecnologicos));
        preguntas.add(new Residuo("Caja de Leche", "Reciclable",R.drawable.caja_leche));
        preguntas.add(new Residuo("Lata", "Reciclable",R.drawable.lata));
        preguntas.add(new Residuo("Bateria Celular", "Peligroso",R.drawable.bateria_celulares));
    }

    private void comparaRespuesta( int index){

            //Busca el id del radioBoton seleccionado
            int id = radioGroup.getCheckedRadioButtonId();
            View radioB = findViewById(id);
            int indice = radioGroup.indexOfChild(radioB);
            radioButton = (RadioButton) radioGroup.getChildAt(indice);
            //obtiene el texto del radio boton seleccionado
            String text = radioButton.getText().toString();
            Residuo residuo = preguntas.get(index);
            Picasso.with(MainActivity.this).load(residuo.getImagen()).fit().into(imageView);
            // compara para determinar si es correcta la respuesta
            if(text.equals(residuo.getTipo())){
                respuestasCorrectas++;

            }
            progressBar.incrementProgressBy(10);
            radioGroup.clearCheck();

    }
    //cambia imagen
    private void cambiarPregunta(int index){

            Residuo residuo = preguntas.get(index);
            Picasso.with(MainActivity.this).load(residuo.getImagen()).fit().into(imageView);

    }

    private void initialize(){
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG, "onAuthStateChanged - inició sesión" + firebaseUser.getUid());
                    Log.w(TAG, "onAuthStateChanged - inició sesión" + firebaseUser.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged - cerró sesión");
                }
            }
        };
    }

    private void cuentanueva( String nombre, String codigo, double puntaje){

        final Map<String, Object> user = new HashMap<>();
        user.put("nombre",nombre);
        user.put("código",codigo);
        user.put("puntaje",puntaje);
        db.collection("user").document().set(user);
    }
}
