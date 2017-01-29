package com.example.marti.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityForResult2 extends AppCompatActivity {

    // Declaramos las variables Button para posteriormente definir
    // sus métodos onClick.
    Button btnAceptar, btnCancelar;
    // Declaramos el EditText para recoger el resultado.
    EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result2);
        // Enlazamos las variables con los componentes que tenemos en el XML
        btnAceptar = (Button)findViewById(R.id.btnAceptar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        etResult = (EditText)findViewById(R.id.editDatos);

        // Definimos el listener que ejecutará el método onClick del botón aceptar.

        btnAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Si el EditText no está vacío recogemos el resultado.
                if (etResult.getText().length() != 0) {
                    // Guardamos el texto del EditText en un String.
                    String resultado = etResult.getText().toString();
                    // Recogemos el intent que ha llamado a esta actividad.
                    Intent i = getIntent();
                    // Le metemos el resultado que queremos mandar a la
                    // actividad principal.
                    i.putExtra("RESULTADO", resultado);
                    // Establecemos el resultado, y volvemos a la actividad
                    // principal. La variable que introducimos en primer lugar
                    // "RESULT_OK" es de la propia actividad, no tenemos que
                    // declararla nosotros.
                    setResult(RESULT_OK, i);

                    // Finalizamos la Activity para volver a la anterior
                    finish();
                } else {
                    // Si no tenía nada escrito el EditText lo avisamos.
                    Toast.makeText(ActivityForResult2.this, "No hay nada", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si se pulsa el botón, establecemos el resultado como cancelado.
                // Al igual que con "RESULT_OK", esta variable es de la activity.
                setResult(RESULT_CANCELED);

                // Finalizamos la Activity para volver a la anterior
                finish();
            }
        });
    }
}
