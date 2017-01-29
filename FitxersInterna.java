package com.example.marti.practicafinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.OutputStreamWriter;

public class FitxersInterna extends AppCompatActivity {

    Button btnCrear;
    Button btnUpdate;
    EditText editTitol;
    EditText editContent;
    String titol="";
    String contingut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers_interna);

        btnCrear = (Button)findViewById(R.id.btnCrear);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        editTitol = (EditText)findViewById(R.id.editTitol);
        editContent=(EditText)findViewById(R.id.editContent);
        titol= String.valueOf(editTitol.getText()+".txt");
        contingut=String.valueOf(editContent.getText());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                try
                {
                    OutputStreamWriter fout =
                            new OutputStreamWriter(
                                    openFileOutput(titol, Context.MODE_PRIVATE));
                    fout.write(contingut);
                    fout.close();

                    Log.i("Ficheros", "Fichero creado!");
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }
            }
        });
    }
}
