package com.final_test.moneylovely.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class NotificationService{


    public static void create(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SchedulingService.class);
            PendingIntent pendingIntent =
                    PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar = Calendar.getInstance();
            int mini = calendar.get(Calendar.MINUTE);            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager
                        .setExact(AlarmManager.RTC_WAKEUP, 37, pendingIntent);
            } else {
                alarmManager
                        .set(AlarmManager.RTC_WAKEUP, 37, pendingIntent);
            }

    }
}


