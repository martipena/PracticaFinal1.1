package com.example.marti.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Intent2 extends AppCompatActivity {

    private TextView txtTlfno;
    private String tlfno;
    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        txtTlfno = (TextView)findViewById(R.id.txtTlfn);
        btnMenu = (Button)findViewById(R.id.btnMenu);

        Bundle bundle = this.getIntent().getExtras();
        //Un botó per tornar directament al menú
        btnMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        //Rep el valor de la anterior activitat i el mostra
        tlfno = bundle.getString("TLFNO");
        txtTlfno.setText("Telefono introducido: " + tlfno);

    }
}
