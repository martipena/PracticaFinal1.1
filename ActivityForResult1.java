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

    // Mètode que s'executa al apretar el botó
    public void rellenarMarca(View v) {
        Intent i = new Intent(this, ActivityForResult2.class);
        //S'inicia la segona activitat, dient-li també que es el camp en el que hem escrit
        startActivityForResult(i, MARCA);
    }

    public void rellenarTipo(View v) {
        Intent i = new Intent(this, ActivityForResult2.class);
        startActivityForResult(i, TIPO);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Es comprova si el resultat de la segona activitat s'ha cancelat.
        if (resultCode == RESULT_CANCELED) {
            // Si s'ha cancelat ho mostrem
            Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // Sinó, agafem el resultat de la segona activitat
            String resultado = data.getExtras().getString("RESULTADO");
            //I es posa la resposta en funció del que habiem triar al principi
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
