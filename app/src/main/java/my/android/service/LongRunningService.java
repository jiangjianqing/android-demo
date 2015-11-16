package my.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.Date;

import my.android.broadcast.AlarmReceiver;
import my.android.utils.LogUtil;

/**
 * Created by ztxs on 15-11-16.
 */
public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.d("LongRunningService","timer service executed at "+new Date().toString());
                //stopSelf();//在service中停止自己
            }
        }).start();

        int interval=60*1000;//间隔1分钟
        long triggerAtTime = SystemClock.elapsedRealtime() + interval;

        Intent alarmReceiverIntent=new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,alarmReceiverIntent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime,pendingIntent);
        //alarmManager.setExact();//使用setExact开头的方法可以保证定时的准确性，而set方法会受节能的影响
        return super.onStartCommand(intent, flags, startId);
    }
}
