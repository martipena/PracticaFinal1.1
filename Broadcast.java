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
            //Get Battery %
            int level = i.getIntExtra("level", 0);
            //Find the progressbar creating in main.xml
            ProgressBar pb = (ProgressBar) findViewById(R.id.progreso);
            //Set progress level with battery % value
            pb.setProgress(level);
            //Find textview control created in main.xml
            TextView tv = (TextView) findViewById(R.id.txtProgreso);
            //Set TextView with text
            tv.setText("Nivel de bateria: " + Integer.toString(level) + "%");
        }
    };
}
