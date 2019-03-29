package com.proyecto.bestplane;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;
        import java.util.Map;

public class FormularioActivity extends AppCompatActivity {

    private static final String TAG = "CrearCuentaActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText edtnombres;
    private EditText edtcodigo;
    private Button btncrearcuenta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Comencemos");

        edtnombres =findViewById(R.id.edtnombres);
        edtcodigo = findViewById(R.id.edtcodigo);
        btncrearcuenta = findViewById(R.id.btncrearcuenta);



        btncrearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormularioActivity.this, MainActivity.class);
                intent.putExtra("nombnre", edtnombres.getText().toString() );
                intent.putExtra("codigo", edtcodigo.getText().toString());
                startActivity(intent);
            }
        });

        edtnombres.addTextChangedListener(loginTextWatcher);
        edtcodigo.addTextChangedListener(loginTextWatcher);


    }


    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nombresentrada = edtnombres.getText().toString().trim();
            String codigoentrada = edtcodigo.getText().toString().trim();

            btncrearcuenta.setEnabled(!nombresentrada.isEmpty() && !codigoentrada.isEmpty());
            if(!nombresentrada.isEmpty() && !codigoentrada.isEmpty()){
                btncrearcuenta.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
            }else {

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!btncrearcuenta.isEnabled()){
                btncrearcuenta.setBackgroundColor(getResources().getColor(R.color.colorDivider));
            }else {


            }

        }
    };


}