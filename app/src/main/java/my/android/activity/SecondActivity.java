package my.android.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ztxs.myapplication2.R;

public class SecondActivity extends BaseActivity {
    /**
     * 启动活动的最佳实践，这样可以方便知道需要的参数
     * @param context
     * @param data1
     * @param data2
     */
    public static void startAction(Context context, String data1, String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("extra_data", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent=this.getIntent();
        String data=intent.getStringExtra("extra_data");
        Log.i(SecondActivity.class.getName(), "收到 传递的数据：" + data);

        Button btnBack=(Button)findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnTel=(Button)findViewById(R.id.button_tel);
        btnTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动拨号Activity
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        Button btnQuitApp=(Button)findViewById(R.id.button_quit_app);
        btnQuitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭所有activity=退出当前应用
                finishAll();
            }
        });
    }
}
