package my.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import my.android.utils.ActivityCollector;

/**
 * Created by ztxs on 15-11-11.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加入ActivityCollector统一管理
        ActivityCollector.addActivity(this);
        //用于观察当前处于哪个Activity
        Log.d("BaseActivity", getClass().getSimpleName());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //从ActivityCollector移除统一管理
        ActivityCollector.removeActivity(this);
    }

    protected void finishAll(){
        ActivityCollector.finishAll();
    }
}
