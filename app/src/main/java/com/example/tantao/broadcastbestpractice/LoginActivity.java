package com.example.tantao.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.account)
    EditText account;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.loginBtn)
    Button loginBtn;
    @InjectView(R.id.remember)
    CheckBox remember;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemeber=sharedPreferences.getBoolean("remember_usermasg",false);
        if (isRemeber){
            String accountstr=sharedPreferences.getString("Account","");
            String passwrodstr=sharedPreferences.getString("Password","");
            account.setText(accountstr);
            password.setText(passwrodstr);
            remember.setChecked(true);
        }

    }

    @OnClick(R.id.loginBtn)
    public void onClick() {
        String accountstr = account.getText().toString();
        String passwordstr = password.getText().toString();
        if (accountstr.equals("admin") && passwordstr.equals("123456")) {
            //写入文件
            editor=sharedPreferences.edit();
            if (remember.isChecked()){
                editor.putBoolean("remember_usermasg",true);
                editor.putString("Account",accountstr);
                editor.putString("Password",passwordstr);
            }else {
                editor.clear();
            }
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "login error!", Toast.LENGTH_SHORT).show();
        }
    }


}
