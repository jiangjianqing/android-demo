package com.example.cz_jjq.baselibrary.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

/**
 * 用于提供统一的Http操作
 * Created by ztxs on 15-11-13.
 */
public class HttpUtil {
    @Deprecated
    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    URL url=new URL(address);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener!=null)
                        listener.onFinish(response.toString());
                }catch (Exception e){
                    if(listener!=null)
                        listener.onError(e);
                    else
                        e.printStackTrace();
                }finally {
                    if(connection!=null)
                        connection.disconnect();
                }
            }
        }).start();

    }

    /**
     * 异步模式使用Http
     * @param url
     * @param listener
     */
    public static void sendAsyncHttpRequest(final String url,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendSyncHttpRequest(url,listener);
            }
        }).start();
    }

    /**
     * 同步模式使用Http，注意一定要在Thread中使用
     * android 4.0之后不允许在MainThread中访问网络，故改为thread方式，
     * 否则会出现：java.util.concurrent.ExecutionException: android.os.NetworkOnMainThreadException
     * @param url 地址
     * @param listener 监听器
     */
    public static void sendSyncHttpRequest(final String url, final HttpCallbackListener listener){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.baidu.com", new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogUtil.e("HttpUtil","sendSyncHttpRequest error:"+responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                listener.onFinish(responseString);
            }
        });
        //public static AsyncHttpClient mHttpc = new AsyncHttpClient();
        /*
        AsyncHttpClient client=new AsyncHttpClient();
        try{
            //setFollowRedirects 用于设置Redirect，非常重要
            Future<Response> f = client.prepareGet(url).setFollowRedirects(true).execute();
            String response=f.get().getResponseBody("utf-8");
            if(listener!=null)
                listener.onFinish(response);
            else
                LogUtil.i("HttpUtil", String.format("%s response:%s", url, response));
        }catch (Exception e){
            if(listener!=null)
                listener.onError(e);
            else
                LogUtil.e("HttpUtil",e.toString());
        }
        client.close();
        */
    }
}
