package com.example.marti.practicafinal;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FitxersExterna extends AppCompatActivity {

    Button btnCrear;
    Button btnUpdate;
    EditText editTitol;
    EditText editContent;
    String titol="";
    String contingut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers_externa);

        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        editTitol = (EditText)findViewById(R.id.editTitol);
        editContent=(EditText)findViewById(R.id.editContent);
        titol= String.valueOf(editTitol.getText()+".txt");
        contingut=String.valueOf(editContent.getText());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                boolean sdDisponible = false;
                boolean sdAccesoEscritura = false;

                //Comproba el estat de la memòria externa (targeta SD)
                String estado = Environment.getExternalStorageState();

                if (estado.equals(Environment.MEDIA_MOUNTED))
                {
                    sdDisponible = true;
                    sdAccesoEscritura = true;
                }
                else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
                {
                    sdDisponible = true;
                    sdAccesoEscritura = false;
                }
                else
                {
                    sdDisponible = false;
                    sdAccesoEscritura = false;
                }

                //Si esta disponible i es pot escriure
                if (sdDisponible && sdAccesoEscritura)
                {
                    try
                    {
                        File ruta_sd_global = Environment.getExternalStorageDirectory();

                        File f = new File(ruta_sd_global.getAbsolutePath(), titol);

                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f));

                        fout.write(contingut);
                        fout.close();

                        Log.i("Ficheros", "Fichero SD creado!");
                    }
                    catch (Exception ex)
                    {
                        Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                    }
                }
            }
        });

    }
}
