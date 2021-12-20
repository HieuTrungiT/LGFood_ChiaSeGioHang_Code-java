package com.example.lgfood_duan1.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.lgfood_duan1.R;

public class mNotificationThemSanPhamMoi extends Service {
    public static final String CHANNEL_ID_THEM_SP_MOI = "CHANNEL_THEM_SP_MOI";
    public static final String CHANNEL_ID_2 = "CHANNEL_2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_THEM_SP_MOI, name, importance);
            channel.setDescription(description);

            //config channel 2
//            CharSequence name_2 = getString(R.string.channel_name_2);
//            String description_2 = getString(R.string.channel_description_2);
//            int importance_2 = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel_2 = new NotificationChannel(CHANNEL_ID_2, name_2, importance_2);
//            channel_2.setDescription(description_2);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);


            notificationManager.createNotificationChannel(channel);
//            notificationManager.createNotificationChannel(channel_2);

        }
    }
}