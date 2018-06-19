package com.example.sarthaktaneja.moviebuff.Notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.sarthaktaneja.moviebuff.R;

import java.util.Date;

public class setReminderr {

    public static void Remind(Date date, String title, String s, Context context) {
        //demo(context);
        Intent alarmIntent = new Intent(context,AlarmReceiver.class);
        alarmIntent.putExtra("message", s);
        alarmIntent.putExtra("title", title);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        //TODO: For demo set after 5 seconds.
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, date.getTime(), pendingIntent);
        Log.d("-----------------------", String.valueOf(date.getTime()- new Date().getTime()));
        //Toast.makeText(context," Alarm Set for"+date.getHours() +"\t"+ date.getMinutes() ,Toast.LENGTH_SHORT).show();
    }

    private static void demo(Context context){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "123")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Hola")
                .setContentText("Amigo")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1,mBuilder.build());
    }
}

