package my.android.utils;


import org.apache.http.client.methods.HttpGetHC4;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by ztxs on 15-11-13.
 */
public class HttpClientUtil {
    public void sendGetRequest(final String address,HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpGetHC4 httpGet = new HttpGetHC4(address);
                CloseableHttpClient httpClient= HttpClients.createDefault();
                //CloseableHttpClient httpclient = HttpClients.custom().useSystemProperties()
                //        .setDefaultCookieStore(cookies)
                //        .build();
                //httpClient.execute(new HttpGetHC4("123"));
                //CloseableHttpResponse response = httpClient.execute(httpGet);
                try {

                } finally {
                    //response.close();
                }
                //UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
            }
        }).start();
    }
}
