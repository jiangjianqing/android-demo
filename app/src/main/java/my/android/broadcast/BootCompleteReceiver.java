package my.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ztxs on 15-11-16.
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //模拟器测试未成功
        //监视系统启动完成广播，需要静态注册配合
        Toast.makeText(context,"系统启动完成",Toast.LENGTH_LONG).show();
    }
}
