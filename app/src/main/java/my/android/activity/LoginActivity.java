package my.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ztxs.myapplication2.R;

import my.android.utils.LogUtil;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passwordEdit;
    private CheckBox chkRememberPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        chkRememberPassword=(CheckBox)findViewById(R.id.remember_password);

        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit.setText(sharedPreferences.getString("account",""));
        chkRememberPassword.setChecked(sharedPreferences.getBoolean("remember_password",false));
        if(chkRememberPassword.isChecked()){
            passwordEdit.setText(sharedPreferences.getString("password",""));
        }

        Button btnLogin=(Button)findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //启动拨号Activity
                //Intent intent = new Intent(Intent.ACTION_DIAL);
                //intent.setData(Uri.parse("tel:10086"));
                //startActivity(intent);

                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("account", account);
                boolean isRemember = chkRememberPassword.isChecked();
                editor.putBoolean("remember_password", isRemember);
                if (isRemember) {
                    editor.putString("password", password);
                } else {
                    editor.putString("password", "");
                }
                editor.commit();

                if (account.equals("admin") && password.equals("123456")) {
                    MainActivity.startAction(LoginActivity.this);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Please login first!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
