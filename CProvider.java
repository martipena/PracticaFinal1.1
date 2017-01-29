package com.example.marti.practicafinal;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CProvider extends AppCompatActivity {

    private Button btnLlamadas;
    private TextView txtResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprovider);

        btnLlamadas = (Button) findViewById(R.id.btnMostrar);
        txtResultados = (TextView) findViewById(R.id.txtTrucades);

        btnLlamadas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String[] projection = new String[]{
                        CallLog.Calls.TYPE,
                        CallLog.Calls.NUMBER,
                        CallLog.Calls.DURATION,
                        CallLog.Calls.DATE};

                Uri llamadasUri = CallLog.Calls.CONTENT_URI;

                ContentResolver cr = getContentResolver();

                if (ActivityCompat.checkSelfPermission(CProvider.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Cursor cur = cr.query(llamadasUri,
                        projection, //Columnas a devolver
                        null,       //Condición de la query
                        null,       //Argumentos variables de la query
                        null);      //Orden de los resultados

                if (cur.moveToFirst())
                {
                    int tipo;
                    String tipoLlamada = "";
                    String telefono;
                    String duracion;
                    String fecha;

                    int colTipo = cur.getColumnIndex(CallLog.Calls.TYPE);
                    int colTelefono = cur.getColumnIndex(CallLog.Calls.NUMBER);
                    int colDuracion = cur.getColumnIndex(CallLog.Calls.DURATION);
                    int colFecha = cur.getColumnIndex(CallLog.Calls.DATE);

                    txtResultados.setText("");

                    do
                    {

                        tipo = cur.getInt(colTipo);
                        telefono = cur.getString(colTelefono);
                        duracion = cur.getString(colDuracion);
                        fecha = cur.getString(colFecha);

                        if(tipo == CallLog.Calls.INCOMING_TYPE)
                            tipoLlamada = "ENTRADA";
                        else if(tipo == CallLog.Calls.OUTGOING_TYPE)
                            tipoLlamada = "SALIDA";
                        else if(tipo == CallLog.Calls.MISSED_TYPE)
                            tipoLlamada = "PERDIDA";

                        txtResultados.append(tipoLlamada + " - " + telefono + " - "+duracion+" - "+fecha+ "\n");

                    } while (cur.moveToNext());
                }
            }
        });
    }
}
