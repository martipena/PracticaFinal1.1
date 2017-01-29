package com.example.marti.practicafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculadoraShared2 extends AppCompatActivity {

    TextView resultat, operation;
    float resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_shared2);

        resultat = (TextView) findViewById(R.id.result);
        operation = (TextView) findViewById(R.id.operation);
        Bundle b = getIntent().getExtras();
        resultado = (float) b.getDouble("res");
        resultat.setText(String.valueOf(b.getDouble("res")));
        operation.setText(b.getString("oper"));
    }

    public void guardar(View v) {
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasCalculadora", Context.MODE_PRIVATE);
        //El botó guarda el resultat en les preferencies compartides.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("resultatAnterior",(int)resultado);
        editor.commit();
        Intent intent = new Intent(CalculadoraShared2.this, CalculadoraShared.class);
        startActivity(intent);

    }
}
