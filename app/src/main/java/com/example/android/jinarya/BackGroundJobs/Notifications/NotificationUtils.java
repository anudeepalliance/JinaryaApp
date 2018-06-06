package com.example.android.jinarya.BackGroundJobs.Notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.android.jinarya.PreNameActivity;
import com.example.android.jinarya.R;

import static com.example.android.jinarya.PreTestActivity.randomNumber;

/**
 * Created by Aadhyamo on 22/12/17.
 */

public class NotificationUtils {


    private static final int  DAILY_MORNING_REMINDER_PENDING_INTENT_ID = 3157;


    private static final String WEEKLY_APP_USAGE_REMINDER_1 =
            "weekly_app_usage_notification_channel_1";

    private static String notificationTitle;

    private static String notificationBody;

    private static Uri notificationTone;

    private static Bitmap largeIcon;


    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.cancelAll();
        Log.i("Notification Utils","clear notifications method called");
    }

    public static void dailyMorningNotification(Context context) {

            int randomNotification = randomNumber(4,1);

            // create separate notification content every time a notification is created

            if ( randomNotification == 1) {

                notificationTitle = context.getString(R.string.app_usage_promoter_1_notification_title);
                notificationBody = context.getString(R.string.app_usage_promoter_1_notification_body);
                largeIcon = BitmapFactory.decodeResource( context.getResources(), R.mipmap.feather_test);

            } else if ( randomNotification == 2) {

                notificationTitle = context.getString(R.string.app_usage_promoter_2_notification_title);
                notificationBody = context.getString(R.string.app_usage_promoter_2_notification_body);
                largeIcon = BitmapFactory.decodeResource( context.getResources(), R.mipmap.ic_couple_love);

            } else {

                notificationTitle = context.getString(R.string.app_usage_promoter_3_notification_title);
                notificationBody = context.getString(R.string.app_usage_promoter_3_notification_body);
                largeIcon = BitmapFactory.decodeResource( context.getResources(), R.mipmap.ic_magnifying_glass);

            }


        notificationTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    WEEKLY_APP_USAGE_REMINDER_1,
                    context.getString(R.string.app_usage_promoter_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, WEEKLY_APP_USAGE_REMINDER_1)

                        .setLargeIcon(largeIcon)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(notificationTitle)
                        .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                        .setContentText(notificationBody)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationBody))
                        .setSound(notificationTone)
                        .setContentIntent(contentIntent(context))
//                        .addAction(yesLetsGo(context))
//                        .addAction(mayBeLater(context))
//                        .addAction(testNotificationAction(context))
                        .setAutoCancel(true);

        Notification notification = notificationBuilder.build();

        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        assert notificationManager != null;

        //ensures that the notification doesn't replace a current one if it exists
        int randomNotificationId = randomNumber(9999999,0);

        notificationManager.notify(randomNotificationId, notification);

    }

    private static PendingIntent contentIntent(Context context) {
        //The activity that the user should be taken to when the notification is clicked
        Intent startActivityIntent = new Intent(context, PreNameActivity.class);
        return PendingIntent.getActivity(
                context,
                DAILY_MORNING_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }


//Work on these notifications later, Explore rich custom notification layout instead

//    private static Action yesLetsGo(Context context) {
//
//        Intent intent = new Intent(context, NotificationIntentService.class);
//
//        intent.setAction(NotificationTasks.ACTION_YES_LETS_GO_NOTIFICATION);
//
//        PendingIntent yesLetsGo = PendingIntent.getService(
//                context,
//                0,
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Action yesLetsGoAction = new Action
//                (R.drawable.ic_launcher_background,
//                        "Yes Lets Go",
//                        yesLetsGo);
//
//        Log.i("Notification Utils","yesLetsGo method called");
//
//        return yesLetsGoAction;
//
//    }
//
//    private static Action mayBeLater(Context context) {
//
//        Intent intent = new Intent(context, NotificationIntentService.class);
//
//        intent.setAction(NotificationTasks.ACTION_DISMISS_NOTIFICATION);
//
//        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
//                context,
//                ACTION_MAY_BE_LATER_PENDING_INTENT_ID,
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Action ignoreReminderAction = new Action(R.drawable.ic_launcher_foreground,
//                "May be Later",
//                ignoreReminderPendingIntent);
//
//        Log.i("Notification Utils","ignoreReminderAction method called");
//        return ignoreReminderAction;
//    }
//
//    private static Action testNotificationAction(Context context) {
//
//        Intent intent = new Intent(context, ReportsListActivity.class);
//        intent.putExtra("clearNotifications", "clearNotifications");
//
//        intent.setAction(NotificationTasks.ACTION_DISMISS_NOTIFICATION);
//
//        PendingIntent ignoreReminderPendingIntent = PendingIntent.getActivity(
//                context,
//                0,
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//
//        Action ignoreReminderAction = new Action(R.drawable.ic_launcher_foreground,
//                "Test Notification",
//                ignoreReminderPendingIntent);
//        Log.i("Notification Utils","ignoreReminderAction method called");
//        return ignoreReminderAction;
//    }

}
