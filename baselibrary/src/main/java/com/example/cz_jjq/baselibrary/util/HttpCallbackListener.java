package com.example.cz_jjq.baselibrary.util;

/**
 * HttpUtil使用的回调接口
 * Created by ztxs on 15-11-13.
 */
public interface HttpCallbackListener {
    /**
     * 当服务器成功响应我们请求的时候调用
     * @param response
     */
    void onFinish(String response);

    /**
     * 当进行网络操作出现错误的时候调用
     * @param e
     */
    void onError(Exception e);
}
