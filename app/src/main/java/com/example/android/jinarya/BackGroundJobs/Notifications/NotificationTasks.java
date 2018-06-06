package com.example.android.jinarya.BackGroundJobs.Notifications;

import android.content.Context;
import android.util.Log;


/**
 * Created by Aadhyamo on 22/12/17.
 */

public class NotificationTasks {

    public static final String ACTION_YES_LETS_GO_NOTIFICATION  = "intent-to-name-activity";

    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";

    public static final String ACTION_DAILY_USAGE_REMINDER = "daily-usage-reminder";


    public static void executeTask(Context context, String action) {



        if (ACTION_YES_LETS_GO_NOTIFICATION.equals(action)) {

            Log.i("Notification Tasks","executeTask ACTION_YES_LETS_GO_NOTIFICATION method called");
            NotificationUtils.clearAllNotifications(context);

        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {

            NotificationUtils.clearAllNotifications(context);
            Log.i("Notification Tasks","executeTask ACTION_DISMISS_NOTIFICATION method called");
        } else if (ACTION_DAILY_USAGE_REMINDER.equals(action)) {

            NotificationUtils.dailyMorningNotification(context);
        }
    }

}
