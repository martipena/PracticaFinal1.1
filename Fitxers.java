package com.example.marti.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fitxers extends AppCompatActivity {

    Button btnInterna;
    Button btnExterna;
    Button btnRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers);

        btnInterna= (Button) findViewById(R.id.btnInterna);

        btnInterna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FitxersInterna.class);
                startActivity(i);
            }
        });

        btnExterna= (Button) findViewById(R.id.btnExterna);

        btnExterna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FitxersExterna.class);
                startActivity(i);
            }
        });

        btnRaw= (Button) findViewById(R.id.btnRaw);

        btnRaw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FitxersRaw.class);
                startActivity(i);
            }
        });

    }
}
