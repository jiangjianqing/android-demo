package com.example.cz_jjq.baselibrary.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.cz_jjq.baselibrary.R;

/**
 * 提供全局Application 变量，如Context等等
 * Created by ztxs on 15-11-13.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        //设置日志等级
        LogUtil.setLogLevel(LogUtil.LogLevel.DEBUG);
        Log.d("MyApplication", "原生log测试下LogUtil");
        LogUtil.d("MyApplication", "测试下LogUtil");

        //JsonSample.illustrateGson();

        //启动定时服务
        //Intent intent=new Intent(context, LongRunningService.class);
        //context.startService(intent);
    }

    /**
     * 获取全局的context，可以在非activity中使用
     * @return
     */
    public static Context getContext() {
        return context;
    }

    private static long exitTime=0L;
    public static void exit(){
        if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getContext(),
                    R.string.confirm_exit,
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            System.exit(0);
        }
    }
}
