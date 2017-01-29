package com.example.marti.practicafinal;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CProvider extends AppCompatActivity {

    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprovider);

        btnMostrar=(Button) findViewById(R.id.btnMostrar);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prova();
            }
        });

    }
    //Quan apretem el botó, s'executa el mètode
        public void prova() {
            if (ContextCompat.checkSelfPermission(CProvider.this,
                    Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                //Comprova que té permisos
                if (ActivityCompat.shouldShowRequestPermissionRationale(CProvider.this,
                        Manifest.permission.READ_CALL_LOG)) {
                    ActivityCompat.requestPermissions(CProvider.this,
                            new String[]{Manifest.permission.READ_CALL_LOG}, 1);
                } else {
                    ActivityCompat.requestPermissions(CProvider.this,
                            new String[]{Manifest.permission.READ_CALL_LOG}, 1);
                }
            } else {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(getCallDetails());
            }
        }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode){
            case 1:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(CProvider.this,
                            Manifest.permission.READ_CALL_LOG)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permisos concedits",Toast.LENGTH_LONG).show();
                        TextView textView = (TextView) findViewById(R.id.textView);
                        textView.setText(getCallDetails());
                    }
                }else{
                    Toast.makeText(this,"No s'han concedit permisos",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private String getCallDetails(){
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);
        //Recull les dades necessaries
        int number =managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type =managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date =managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration =managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Trucades:\n\n");
        while(managedCursor.moveToNext()){
            String phNumber=managedCursor.getString(number);
            String callType=managedCursor.getString(type);
            String callDate=managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
            String dateString = formatter.format(callDayTime);
            String callDuration = managedCursor.getString(duration);
            String dir=null;
            int dircode=Integer.parseInt(callType);
            switch(dircode){
                case CallLog.Calls.OUTGOING_TYPE:
                    dir="SORTIDA";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir="ENTRADA";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir="PERDUDA";
                    break;
            }
            //Mostra les dades
            sb.append("\nNúmero de telèfon: "+phNumber+" \nTipus de trucada: "+dir+"\nDia de la trucada: "+dateString+
                        " \nDuració: "+callDuration);
            sb.append("\n--------------------------------");
        }
        managedCursor.close();
        return sb.toString();
    }
}
