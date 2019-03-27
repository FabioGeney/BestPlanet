package com.proyecto.bestplane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Residuo> preguntas = new ArrayList<>();
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
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imagen);
        button = findViewById(R.id.boton);
        radioGroup = findViewById(R.id.radioGroup);
        progressBar = findViewById(R.id.progreso);
        progressBar.setMax(100);

        guardarPreguntas();
        cambiarPregunta(indice);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        comparaRespuesta(indice);
                        indice++;

                        if(indice < preguntas.size()){
                            cambiarPregunta(indice);
                        }else {
                            double calificacion = 5*(respuestasCorrectas/preguntas.size());
                            Toast.makeText(MainActivity.this, ""+ calificacion + " " + indice + " " + respuestasCorrectas, Toast.LENGTH_SHORT).show();
                        }
                        button.setBackgroundColor(getResources().getColor(R.color.colorBoton));

                    }
                });

            }
        });


    }

    private void guardarPreguntas(){

        preguntas.add(new Residuo("Papel", "Reciclable",R.drawable.ico));
        preguntas.add(new Residuo("Baterias", "Peligroso",R.drawable.bat));
        preguntas.add(new Residuo("Jeringa", "Peligroso",R.drawable.ico));
        preguntas.add(new Residuo("Icopor", "Reciclable",R.drawable.bat));
    }

    private void comparaRespuesta( int index){

        int id = radioGroup.getCheckedRadioButtonId();
        View radioB = findViewById(id);
        int indice = radioGroup.indexOfChild(radioB);
        radioButton = (RadioButton) radioGroup.getChildAt(indice);
        String text = radioButton.getText().toString();
        Residuo residuo = preguntas.get(index);
        Picasso.with(MainActivity.this).load(residuo.getImagen()).fit().into(imageView);
        if(text.equals(residuo.getTipo())){
            respuestasCorrectas++;
            Toast.makeText(MainActivity.this, " gj "+ index, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(MainActivity.this, "bj", Toast.LENGTH_SHORT).show();
        }
        progressBar.incrementProgressBy(25);
        radioGroup.clearCheck();

    }

    private void cambiarPregunta(int index){
        Residuo residuo = preguntas.get(index);
        Picasso.with(MainActivity.this).load(residuo.getImagen()).fit().into(imageView);
    }
}
