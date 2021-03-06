package com.example.cz_jjq.baselibrary.activity;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztxs on 15-11-11.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity:activities) {
            if(!activity.isFinishing())
                activity.finish();
        }
        activities.clear();
        Log.d("ActivityCollector","invoking finishAll.当前应用将被关闭");
    }
}
