package com.example.marti.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnIntent;
    Button btnShared;
    Button btnFitxers;
    Button btnBroadcast;
    Button btnResult;
    Button btnMissatge;
    Button btnContent;
    Button btnSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIntent= (Button) findViewById(R.id.btnIntent);

        btnIntent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Intent1.class);
                startActivity(i);
            }
        });

        btnShared= (Button) findViewById(R.id.btnShared);

        btnShared.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CalculadoraShared.class);
                startActivity(i);
            }
        });

        btnFitxers= (Button) findViewById(R.id.btnFitxers);

        btnFitxers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Fitxers.class);
                startActivity(i);
            }
        });

        btnBroadcast= (Button) findViewById(R.id.btnBroadcast);

        btnBroadcast.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Broadcast.class);
                startActivity(i);
            }
        });

        btnResult= (Button) findViewById(R.id.btnResult);

        btnResult.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ActivityForResult1.class);
                startActivity(i);
            }
        });

        btnMissatge= (Button) findViewById(R.id.btnMissatge);

        btnMissatge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Missatges.class);
                startActivity(i);
            }
        });

        btnContent= (Button) findViewById(R.id.btnContent);

        btnContent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CProvider.class);
                startActivity(i);
            }
        });

        btnSQLite= (Button) findViewById(R.id.btnSQLite);

        btnSQLite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SQLite1.class);
                startActivity(i);
            }
        });
    }
}
