package com.bawei.day2_14.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.day2_14.R;
import com.bawei.day2_14.presenter.RegistPresenter;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_pwd;
    private Button button;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        button = findViewById(R.id.button);

        registPresenter = new RegistPresenter();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        if (phone.isEmpty()||pwd.isEmpty()){
            Toast.makeText(this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        registPresenter.sendParameter(phone,pwd);
    }
}
