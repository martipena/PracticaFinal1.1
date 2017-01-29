package com.example.marti.practicafinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBatteryReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();

        //Toast.makeText(context,"Action: "+action, Toast.LENGTH_SHORT).show();

        String strAction;
        if(action == Intent.ACTION_BATTERY_LOW) {
            strAction = "BATTERY_LOW";
        }
        if(action == Intent.ACTION_BATTERY_OKAY){
            strAction="BATTERY_OKAY";
        }else if(action == Intent.ACTION_POWER_CONNECTED){
            strAction="Bateria conectada";
        }else if(action == Intent.ACTION_POWER_DISCONNECTED){
            strAction="Bateria desconectada";
        }else
            strAction="unknown!";

        Toast.makeText(context,"Action: "+action, Toast.LENGTH_SHORT).show();

    }
}
