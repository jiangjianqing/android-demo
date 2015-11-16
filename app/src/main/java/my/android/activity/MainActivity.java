package my.android.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ztxs.myapplication2.R;

import my.android.broadcast.NetworkChangeReceiver;

public class MainActivity extends BaseActivity {

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar没有成功
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btnOpenSecondActivity=(Button)findViewById(R.id.button_open_second_activity);
        btnOpenSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implicit隐式调用
                //Intent intent=new Intent("myapplication.activity.second.Start");
                //intent.addCategory("myapplication.category.Second");
                //intent.putExtra("extra_data","hello,second activity!");

                //explicit显式调用
                //Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                //startActivity(intent);

                SecondActivity.startAction(MainActivity.this, "hello,second activity!", "");

            }
        });

        Button buttonQuit=(Button)findViewById(R.id.button_quit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭activity，等同于back键
                finish();
            }
        });

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.d("MainActivity", "输出屏幕像素密度dpi：");
        Log.d("MainActivity", "xdpi is " + xdpi);
        Log.d("MainActivity", "ydpi is " + ydpi);

        //注册网络状态BroadcastReceiver
        IntentFilter intentFilter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        //intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        //用父类的注册函数代替natively registerReceiver
        registerGlobalReceiver(networkChangeReceiver, intentFilter, "MainActivity networkChangeReceiver");
        //registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //动态注册的广播接收器一定都要取消注册,
        //通过wrap的registerLocalReceiver和registerGlobalReceiver注册的由BaseActivity完成unregister工作
        //LogUtil.d("MainActivity","注销NetworkChangeReceiver");
        //unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        boolean isProcessed=true;
        switch(item.getItemId()){
            case R.id.action_add:
                //弹出一个会自动消失的提示框
                Toast.makeText(this,"点击了add menu",Toast.LENGTH_LONG).show();
                Log.w("MainActivity", "点击了add menu");
                break;
            case R.id.action_settings:
                Toast.makeText(this,"点击了setting menu",Toast.LENGTH_SHORT).show();
                Log.w("MainActivity","点击了setting menu");
                break;
            case R.id.action_log:
                Log.w("MainActivity","点击了log menu");
                break;
            case R.id.action_webview:
                WebActivity.startAction(this,"http://www.baidu.com");
                break;
            case R.id.action_httpUrlConnection:
                HttpUrlConnectionActivity.startAction(this,"http://www.baidu.com");
                break;
            default:
                isProcessed=false;
        }
        if (isProcessed)
            return true;

        return super.onOptionsItemSelected(item);
    }
}
