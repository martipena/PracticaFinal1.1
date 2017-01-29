package com.example.marti.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityForResult2 extends AppCompatActivity {

    Button btnAceptar, btnCancelar;
    EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result2);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        etResult = (EditText)findViewById(R.id.editDatos);

        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Si el edittext no esta buit, agafarem el resultat
                if (etResult.getText().length() != 0) {
                    String resultado = etResult.getText().toString();
                    // Agafem el intent que ha cridat a aquesta activitat
                    Intent i = getIntent();
                    // Li posem el resultat que volem tornar
                    i.putExtra("RESULTADO", resultado);
                    // Posem el resultat i el tornem
                    setResult(RESULT_OK, i);

                    finish();
                } else {
                    // Si no hi ha res al editText, surt un avís.
                    Toast.makeText(ActivityForResult2.this, "No hay nada", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Si s'apreta el resultat sera cancelat
                setResult(RESULT_CANCELED);

                finish();
            }
        });
    }
}
