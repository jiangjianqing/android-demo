package my.android.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

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
        LogUtil.setLogLevel(LogUtil.LogLevel.INFO);
        Log.d("MyApplication","原生log测试下LogUtil");
        LogUtil.d("MyApplication","测试下LogUtil");
    }

    /**
     * 获取全局的context，可以在非activity中使用
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
