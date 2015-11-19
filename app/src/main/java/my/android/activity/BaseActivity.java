package my.android.activity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import my.android.utils.ActivityCollector;
import my.android.utils.LogUtil;
import my.android.utils.MyApplication;

/**
 * Created by ztxs on 15-11-11.
 */
public class BaseActivity extends AppCompatActivity {

    private class ReceiverItem{
        public BroadcastReceiver receiver;
        /**
         * 是否通过LocalBroadcastManager注册
         */
        public boolean isLocal;
        /**
         * 备注信息
         */
        public CharSequence memo;
    }
    private List<ReceiverItem> receivers=new ArrayList<ReceiverItem>();

    protected LocalBroadcastManager localBroadcastManager=null;

    private boolean enableExitConfirm=false;

    private void addReceiverToList(BroadcastReceiver receiver,boolean isLocal,CharSequence memo){
        ReceiverItem item=new ReceiverItem();
        item.receiver=receiver;
        item.isLocal=isLocal;
        item.memo=memo;
        receivers.add(item);
    }

    private ReceiverItem removeReceiverFromList(BroadcastReceiver receiver){
        ReceiverItem ret=null;
        for(ReceiverItem item :receivers){
            if(item.receiver==receiver){
                ret=item;
                receivers.remove(item);
                break;
            }
        }
        return ret;
    }

    /**
     * 清空receivers(仅仅在onDestroy中调用)
     */
    private void clearReceivers(){
        for(ReceiverItem item :receivers){
            if(item.isLocal)
                unregisterLocalReceiver(item.receiver);
            else
                unregisterGlobalReceiver(item.receiver);
        }
        receivers.clear();
    }


    /**
     * 获取本地广播实例
     */
    protected void enableLocalBroadcastmanager(){
        //获取本地广播管理器
        localBroadcastManager=LocalBroadcastManager.getInstance(this);
    }

    /**
     * 注销LocalBroadcastReceiver
     * @param receiver The BroadcastReceiver to unregister.
     */
    public void unregisterLocalReceiver(BroadcastReceiver receiver){
        ReceiverItem item=removeReceiverFromList(receiver);
        if(item!=null && item.isLocal==true){
            localBroadcastManager.unregisterReceiver(receiver);
            LogUtil.d("BaseActivity", "注销LocalReceiver:" + item.memo);
        }else{
            LogUtil.d("BaseActivity","unregisterLocalReceiver 出现异常，ReceiverItem不符合条件");
        }
    }

    /**
     * 注册LocalBroadcastReceiver(本地广播是无法通过静态注册的方式来接收)
     * @param receiver
     * @param filter
     */
    public void registerLocalReceiver(BroadcastReceiver receiver,IntentFilter filter){
        registerLocalReceiver(receiver,filter,"");
    }

    /**
     * 注册LocalBroadcastReceiver(本地广播是无法通过静态注册的方式来接收)
     * @param receiver The BroadcastReceiver to register.
     * @param filter The BroadcastReceiver's filter param
     */
    public void registerLocalReceiver(BroadcastReceiver receiver,IntentFilter filter,CharSequence memo){
        if(localBroadcastManager==null)
            enableLocalBroadcastmanager();

        localBroadcastManager.registerReceiver(receiver, filter);
        addReceiverToList(receiver, true, memo);
    }

    /**
     * 注册GlobalBroadcastReceiver(wraped natively register function)
     * @param receiver
     * @param filter
     */
    public void registerGlobalReceiver(BroadcastReceiver receiver, IntentFilter filter){
        registerGlobalReceiver(receiver, filter, "");
    }
    /**
     * 注册GlobalBroadcastReceiver(wraped natively register function)
     * @param receiver
     * @param filter
     */
    public void registerGlobalReceiver(BroadcastReceiver receiver,IntentFilter filter,CharSequence memo){
        registerReceiver(receiver, filter);
        addReceiverToList(receiver, false, memo);
    }

    /**
     * 注销GlobalBroadcastReceiver(wraped natively unregister function)
     * @param receiver
     */
    public void unregisterGlobalReceiver(BroadcastReceiver receiver){
        ReceiverItem item=removeReceiverFromList(receiver);
        if(item!=null && item.isLocal==false){
            unregisterReceiver(receiver);
            LogUtil.d("BaseActivity","注销GlobalReceiver:"+item.memo);
        }else{
            LogUtil.d("BaseActivity","unregisterGlobalReceiver 出现异常，ReceiverItem不符合条件");
        }
    }

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
        //清空所有注册的BroadcastReceiver（必须由registerLocal和registerGlobal注册方式）
        clearReceivers();
        //从ActivityCollector移除统一管理
        ActivityCollector.removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这里改为两次点击Back键退出
        if (enableExitConfirm && keyCode == KeyEvent.KEYCODE_BACK) {
            MyApplication.exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 当前页面作为主页面，退出需要确认
     * @param enabled
     */
    public void setEnableExitConfirm(boolean enabled){
        enableExitConfirm=enabled;
    }

    protected void finishAll(){
        ActivityCollector.finishAll();
    }
}
