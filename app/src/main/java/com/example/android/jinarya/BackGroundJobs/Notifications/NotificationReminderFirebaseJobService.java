package com.example.android.jinarya.BackGroundJobs.Notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Aadhyamo on 23/12/17.
 */

public class NotificationReminderFirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        mBackgroundTask = new AsyncTask() {


            @Override
            protected Object doInBackground(Object[] params) {

                Context context = NotificationReminderFirebaseJobService.this;
                NotificationTasks.executeTask(context, NotificationTasks.ACTION_DAILY_USAGE_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {


                jobFinished(jobParameters, false);
            }
        };


        mBackgroundTask.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}