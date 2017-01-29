package com.example.marti.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Intent1 extends AppCompatActivity {

    private EditText txtTlfno;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);

        txtTlfno = (EditText)findViewById(R.id.txtPhone);
        btnAceptar = (Button)findViewById(R.id.btnAceptar);

        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        String nomSharedPrefs = prefs.getString("NOMBRE", "Nombre por defecto");

        //Implementem el event del botó
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Es crea el intent
                Intent intent =
                        new Intent(Intent1.this, Intent2.class);

                //Es crea la informació per passar a l'altra activitat
                Bundle b = new Bundle();
                b.putString("TLFNO", txtTlfno.getText().toString());

                //S'afegeix la informació al intent
                intent.putExtras(b);

                //Iniciem la nova activitat
                startActivity(intent);
            }
        });
    }
}
