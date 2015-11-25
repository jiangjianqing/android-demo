package my.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ztxs.myapplication2.R;

import my.android.fragment.WebSiteContentFragment;

public class WebActivity extends AppCompatActivity {

    private WebSiteContentFragment webContentFragment;
    /**
     * 根据传入的url打开网页
     * @param context
     * @param url
     */
    public static void startAction(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
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

        Intent intent=this.getIntent();
        String url=intent.getStringExtra("url");

        webContentFragment=(WebSiteContentFragment)getFragmentManager().findFragmentById(R.id.web_site_content_fragment);
        webContentFragment.refreshFragment(url);
    }

    @Override
    public void onBackPressed() {
        if(webContentFragment.canGoBack()){
            webContentFragment.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
