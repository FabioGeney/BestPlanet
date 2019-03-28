package com.proyecto.bestplane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Calificacion extends AppCompatActivity {

    private TextView nota;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);

        nota = findViewById(R.id.nota);
        ratingBar = findViewById(R.id.estrallas);

        int respuestasCorrectas = 0;
        getIntent().getIntExtra("rCorrectas", respuestasCorrectas);
        int total = 0;
        getIntent().getIntExtra("totalPreguntas", total);
        Toast.makeText(this, ""+respuestasCorrectas+" "+total, Toast.LENGTH_SHORT).show();


    }
}
