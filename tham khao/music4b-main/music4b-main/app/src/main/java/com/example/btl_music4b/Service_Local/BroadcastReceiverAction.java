package com.example.btl_music4b.Service_Local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BroadcastReceiverAction extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int actionMusic = intent.getIntExtra("action_music", 0);

        Intent intentService = new Intent(context, ForegroundServiceControl.class);
        intentService.putExtra("action_music_service", actionMusic);

        context.startService(intentService);
    }
}
