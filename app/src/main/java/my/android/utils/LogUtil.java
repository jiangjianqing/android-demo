package my.android.utils;

import android.util.Log;

/**
 * 模仿Log4j的规则
 * Created by ztxs on 15-11-13.
 */
public class LogUtil {
    public static enum LogLevel{VERBOSE,DEBUG,INFO,WARN,ERROR,NOTHING};

    private static LogLevel level = LogLevel.VERBOSE;

    public static void setLogLevel(LogLevel lvl){
        level=lvl;
    }

    public static void v(String tag, String msg) {
        if (level.ordinal() <= LogLevel.VERBOSE.ordinal()) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level.compareTo(LogLevel.DEBUG)!=1) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level.compareTo(LogLevel.INFO)!=1) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level.compareTo(LogLevel.WARN)!=1) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level.compareTo(LogLevel.ERROR)!=1) {
            Log.e(tag, msg);
        }
    }
}
