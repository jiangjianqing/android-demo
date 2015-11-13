package my.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ztxs.myapplication2.R;

import my.android.utils.HttpCallbackListener;
import my.android.utils.HttpUtil;
import my.android.utils.LogUtil;

public class HttpUrlConnectionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewResponse;
    public static void startAction(Context context, String url) {
        Intent intent = new Intent(context, HttpUrlConnectionActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);

        Button btnSendRequest=(Button)findViewById(R.id.send_request);
        btnSendRequest.setOnClickListener(this);

        textViewResponse=(TextView)findViewById(R.id.response);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request:
                HttpUtil.sendHttpRequest("http://www.baidu.com", new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        textViewResponse.setText(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        LogUtil.e("HttpUrlConnectionActivity",e.getMessage());
                    }
                });
                break;
            default:
        }
    }
}
