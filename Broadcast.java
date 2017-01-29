package com.example.marti.practicafinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Broadcast extends AppCompatActivity {

    Button btnBateria;
    TextView txtBateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        registerReceiver(mBatInfoReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));

        btnBateria = (Button) findViewById(R.id.btnBateria);
        txtBateria = (TextView) findViewById(R.id.txtBateria);

        btnBateria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txtBateria.setText(readBattery());
            }

            public String readBattery(){
                StringBuilder sb = new StringBuilder();
                int status;

                IntentFilter batteryIntentFilter = new IntentFilter((Intent.ACTION_BATTERY_CHANGED));
                Intent batteryIntent = registerReceiver(null, batteryIntentFilter);

                boolean present1 = batteryIntent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
                sb.append("PRESENT: " + present1 + "\n");

                status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                //Comprovem el estat actual de la bateria
                if(status == BatteryManager.BATTERY_STATUS_CHARGING){
                    sb.append("BATEERY_STATUS_CHARGING\n");
                }
                if(status == BatteryManager.BATTERY_STATUS_FULL){
                    sb.append("BATTERY_STATUS_FULL\n");
                }

                int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                if(plugged == BatteryManager.BATTERY_PLUGGED_USB){
                    sb.append("BATTERY_PLUGGED_USB\n");
                }
                if(plugged == BatteryManager.BATTERY_PLUGGED_AC){
                    sb.append("BATTERY_PLUGGED_AC\n");
                }

                return sb.toString();
            }
        });
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        //When Event is published, onReceive method is called
        public void onReceive(Context c, Intent i) {
            //Obtenim el % de bateria restant
            int level = i.getIntExtra("level", 0);
            //Posem una barra de progrés
            ProgressBar pb = (ProgressBar) findViewById(R.id.progreso);
            //I li posem el valor igual al del % de la bateria
            pb.setProgress(level);
            //Despres ho posem en el textView
            TextView tv = (TextView) findViewById(R.id.txtProgreso);
            tv.setText("Nivel de bateria: " + Integer.toString(level) + "%");
        }
    };
}
