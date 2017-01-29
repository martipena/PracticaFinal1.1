package com.example.marti.practicafinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculadoraShared extends AppCompatActivity {

    private TextView pantallaResult;
    private long dig1 = -1, dig2 = -1;
    private double resultado = 0;
    private String oper = "";
    private String op;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_shared);
        pantallaResult = (TextView) findViewById(R.id.pantalla);
        Button bVolver = (Button) findViewById(R.id.btnVolver);
        bVolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CalculadoraShared.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void clickBotonNum(View v) {

        Button botonPulsado = (Button) findViewById(v.getId());
        pantallaResult.setText(pantallaResult.getText() + botonPulsado.getText().toString());

    }

    public void clickOperador(View v) {

        if (pantallaResult.getText() != "") {
            if (dig1 == -1) {
                dig1 = Long.parseLong(pantallaResult.getText().toString());
                pantallaResult.setText("");
            }
        }else {
            if (dig1 == -1) {
                dig1 = 0;
            }
        }

        Button botonPulsado = (Button) findViewById(v.getId());
        op = botonPulsado.getText().toString();

    }

    public void clickIgual(View v) {

        if (pantallaResult.getText() != "") {
            if (dig1 != -1) {
                dig2 = Long.parseLong(pantallaResult.getText().toString());
                pantallaResult.setText(String.valueOf(dig1+dig2));
            }
        }
        //Al apretar el botó de igual, ens portara a la segona activitat
        Intent intent = new Intent(CalculadoraShared.this,CalculadoraShared2.class);
        Bundle b = new Bundle();

        switch (op) {
            case "+" : {
                resultado = dig1 + dig2;
                oper = dig1 + " + " + dig2;
                break;
            }
            case "-" : {
                resultado = dig1 - dig2;
                oper = dig1 + " - " + dig2;
                break;
            }
            case "/" : {
                if (dig1 > 0 && dig2 == 0) {
                    resultado = 0;
                } else {
                    resultado = (double) dig1 / dig2;
                }
                oper = dig1 + " / " + dig2;
                break;
            }
            case "*" : {
                resultado = dig1 * dig2;
                oper = dig1 + " * " + dig2;
                break;
            }
        }

        b.putDouble("res", resultado);
        b.putString("oper",oper);
        intent.putExtras(b);
        startActivity(intent);

        dig1 = -1;
        dig2 = -1;
        pantallaResult.setText("");
    }

    public void clickClean(View v) {
        dig1 = -1;
        dig2 = -1;
        pantallaResult.setText("");
    }

    public void recuperar(View v) {
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasCalculadora", Context.MODE_PRIVATE);
        //Al apretar el botó de recuperar ens mostra l'ultim resultat guardat
        int resultatAnterior = prefs.getInt("resultatAnterior", 0);
        pantallaResult.setText(String.valueOf(resultatAnterior));
    }
}
