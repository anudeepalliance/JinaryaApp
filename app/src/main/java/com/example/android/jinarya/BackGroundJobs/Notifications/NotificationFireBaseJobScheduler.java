package com.example.android.jinarya.BackGroundJobs.Notifications;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Aadhyamo on 23/12/17.
 */

public class NotificationFireBaseJobScheduler {

    //notify 5 days post first app open
    private static final int REMINDER_INTERVAL_MINUTES = 36000;

    //the no. of minutes which is a gap between the first and second the the subsequent notifications
    private static final int SYNC_FLEXTIME_MINUTES = 36000;

    private static final int SYNC_FLEXTIME_SECONDS =
            (int) (TimeUnit.MINUTES.toSeconds(SYNC_FLEXTIME_MINUTES));


    private static final String REMINDER_JOB_TAG = "app_usage_promoter_tag";

    private static boolean sInitialized;

    synchronized public static void dailyUsageReminder(@NonNull final Context context) {

        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);

        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(NotificationReminderFirebaseJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_MINUTES,
                        SYNC_FLEXTIME_MINUTES))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintReminderJob);

        sInitialized = true;
    }

}
