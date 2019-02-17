package com.bawei.rikao2_15.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.rikao2_15.R;
import com.bawei.rikao2_15.base.BaseActivity;
import com.bawei.rikao2_15.presenter.LoginPresenter;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private EditText et_phone;
    private EditText et_pwd;
    private Button button;
    private LoginPresenter loginPresenter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVIew() {
        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        button = findViewById(R.id.button);

    }

    @Override
    protected void initData() {
        loginPresenter = new LoginPresenter();
        //设置点击事件
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        if (phone.length()<11){
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }
        else if (pwd.length()<3){
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }
        else {

            loginPresenter.sendParameter();
        }

    }
}
