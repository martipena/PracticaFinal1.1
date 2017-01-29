package com.example.marti.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityForResult1 extends AppCompatActivity implements View.OnClickListener {

    private final static int MARCA = 0;
    private final static int TIPO = 1;

    EditText etMarca, etTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        etMarca = (EditText) findViewById(R.id.editMarca);
        etTipo = (EditText) findViewById(R.id.editTipo);

        findViewById(R.id.btnOk1).setOnClickListener(this);
        findViewById(R.id.btnOk2).setOnClickListener(this);
    }

    // Método que se ejecuta al pulsar el botón btnMarca:
    public void rellenarMarca(View v) {
        Intent i = new Intent(this, ActivityForResult2.class);
        // Iniciamos la segunda actividad, y le indicamos que la iniciamos
        // para rellenar la marca:
        startActivityForResult(i, MARCA);
    }
    // Método que se ejecuta al pulsar el botón btnTipo
    public void rellenarTipo(View v) {
        Intent i = new Intent(this, ActivityForResult2.class);
        // Iniciamos la segunda actividad, y le indicamos que la iniciamos
        // para rellenar el tipo:
        startActivityForResult(i, TIPO);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad es "RESULT_CANCELED".
        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getExtras().getString("RESULTADO");
            // Y tratamos el resultado en función de si se lanzó para rellenar el
            // nombre o el apellido.
            switch (requestCode) {
                case MARCA:
                    etMarca.setText(resultado);
                    break;
                case TIPO:
                    etTipo.setText(resultado);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnOk1:
                rellenarMarca(v);
                break;
            case R.id.btnOk2:
                rellenarTipo(v);
                break;
        }
    }
}
