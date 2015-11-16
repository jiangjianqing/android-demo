package my.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import my.android.service.LongRunningService;

/**
 * Created by ztxs on 15-11-16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent=new Intent(context, LongRunningService.class);
        context.startService(newIntent);
    }
}
