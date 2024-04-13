package com.wilgon.tpwortiz.ui.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.telephony.PhoneNumberUtils;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean modoWifi = intent.getBooleanExtra("connected", true);

        if (modoWifi) {
            Intent intenta = new Intent(Intent.ACTION_CALL);
            intenta.setData(Uri.parse("tel:" + "2664553747"));
            //intenta.setData(Uri.parse("tel:" + "2664309087"));
            intenta.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intenta);
        }else{
            Toast.makeText(context,"wifi desconectado, no llama", Toast.LENGTH_LONG).show();
        }
    }
}
