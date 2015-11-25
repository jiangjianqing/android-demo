package my.android.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cz_jjq.baselibrary.activity.BaseActivity;
import com.example.ztxs.myapplication2.R;

import my.android.broadcast.NetworkChangeReceiver;
import my.android.fragment.WebSiteContentFragment;
import my.android.fragment.WebSiteNameFragment;

public class MainActivity extends BaseActivity implements WebSiteNameFragment.OnWebSiteNameChangeListener{

    public static void startAction(Context context){
        Intent intent=new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    private NetworkChangeReceiver networkChangeReceiver;

    private WebSiteNameFragment webSiteNameFragment;
    private WebSiteContentFragment webSiteContentFragment;


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

        //当前页面作为主页面，退出是需要确认！！
        setEnableExitConfirm(true);

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.d("MainActivity", "输出屏幕像素密度dpi：");
        Log.d("MainActivity", "xdpi is " + xdpi);
        Log.d("MainActivity", "ydpi is " + ydpi);

        //implicit隐式调用
        //Intent intent=new Intent("myapplication.activity.second.Start");
        //intent.addCategory("myapplication.category.Second");
        //intent.putExtra("extra_data","hello,second activity!");

        //explicit显式调用
        //Intent intent=new Intent(MainActivity.this,SecondActity.class);
        //startActivity(intent);

        //注册网络状态BroadcastReceiver
        IntentFilter intentFilter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        //intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        //用父类的注册函数代替natively registerReceiver
        registerGlobalReceiver(networkChangeReceiver, intentFilter, "MainActivity networkChangeReceiver");
        //registerReceiver(networkChangeReceiver,intentFilter);

        //从Activity中获取Fragment
        webSiteNameFragment=(WebSiteNameFragment)getFragmentManager().findFragmentById(R.id.web_site_name_fragment);
        webSiteNameFragment.testInvoke();

        //根据判断是否存在R.id.web_site_content_layout，来确定是大屏还是小屏
        View fragmentView=findViewById(R.id.web_site_content_layout);
        if(fragmentView!=null) {
            webSiteContentFragment = (WebSiteContentFragment) getFragmentManager().findFragmentById(R.id.web_site_content_fragment);
        }
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
            case R.id.action_settings:
                Toast.makeText(this,"点击了setting menu",Toast.LENGTH_SHORT).show();
                Log.w("MainActivity","点击了setting menu");
                break;
            case R.id.action_forceoffline:
                Intent intent=new Intent("my.android.receiver.FORCE_OFFLINE");
                sendBroadcast(intent);
                break;
            case R.id.action_quit:
                //关闭activity，等同于back键
                finish();
                break;
            case R.id.action_notification:
                NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //underlying deprecated
                //Notification notification = new Notification(R.drawable.ic_launcher, "This is ticker text", System.currentTimeMillis());
                //notification.setLatestEventInfo(this, "This is content title","This is content text", null);
                Intent intent2=new Intent("my.android.receiver.FORCE_OFFLINE");
                PendingIntent pi=PendingIntent.getBroadcast(this, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
                //PendingIntent.getService(...)//调用Service
                //PendingIntent.getActivities(...)//调用Activitiy
                //PendingIntent.getBroadcast(...)//调用Broadcast
                Notification.Builder builder=new Notification.Builder(this);
                builder.setTicker("this is ticker info")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("this is content title")
                        .setContentInfo("this is content info")
                        .setContentText("this is content text")
                        .setContentIntent(pi)
                        .setWhen(System.currentTimeMillis() + 2000);

                //trigger notification with sound
                //Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
                //builder.setSound(soundUri);

                //trigger notification with vibrate
                long[] vibrates={0,1000,1000,1000};
                builder.setVibrate(vibrates);

                //determinate all effects via system config
                //builder.setDefaults(Notification.DEFAULT_ALL);

                //argb用于控制 LED 灯的颜色,一般有红绿蓝三种颜色可选
                //OnMS 用于指定 LED 灯亮起的时长,以毫秒为单位
                //OffMS用于指定 LED 灯暗去的时长,以毫秒为单位
                builder.setLights(Color.GREEN,1000,500);

                Notification notification = builder.build();
                int notificationId=1;
                notificationManager.notify(notificationId, notification);
                //notification需要在调用cancel后才能消失
                //notificationManager.cancel(notificationId);
                break;
            default:
                isProcessed=false;
        }
        if (isProcessed)
            return true;

        return super.onOptionsItemSelected(item);
    }

    /**
     * WebSiteNameFragment中传递来的改变url事件
     * @param url
     */
    @Override
    public void onChangeUrl(String url) {
        if(webSiteContentFragment!=null){
            webSiteContentFragment.refreshFragment(url);
        }else{
            WebActivity.startAction(this,url);
        }
    }

}
