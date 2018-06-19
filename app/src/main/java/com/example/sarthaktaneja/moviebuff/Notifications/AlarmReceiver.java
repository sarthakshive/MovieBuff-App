package com.example.sarthaktaneja.moviebuff.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.sarthaktaneja.moviebuff.R;

public class AlarmReceiver extends BroadcastReceiver
       {
           @Override
           public void onReceive(Context context, Intent intent) {

           String message = intent.getStringExtra("message");
           String title = intent.getStringExtra("title");
           Toast.makeText(context,"Recieved",Toast.LENGTH_SHORT).show();
               NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "123")
                       .setSmallIcon(R.mipmap.ic_launcher)
                       .setContentTitle(title)
                       .setContentText(message)
                       .setPriority(NotificationCompat.PRIORITY_DEFAULT);

               NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

               notificationManagerCompat.notify(1,mBuilder.build());

           }
       }
