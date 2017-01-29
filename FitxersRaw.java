package com.example.marti.practicafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FitxersRaw extends AppCompatActivity {

    Button btnRaw;
    EditText editRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers_raw);

        editRaw = (EditText)findViewById(R.id.editRaw);

        btnRaw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                String linea = "";

                try
                {
                    InputStream fraw =
                            getResources().openRawResource(R.raw.prueba_raw);

                    BufferedReader brin =
                            new BufferedReader(new InputStreamReader(fraw));

                    linea = brin.readLine();
                    fraw.close();
                    editRaw.setText(linea);

                    Log.i("Ficheros", "Fichero RAW leido!");
                    Log.i("Ficheros", "Texto: " + linea);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");;
                }
            }
        });
    }
}
