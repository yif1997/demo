package com.bawei.zhou1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.zhou1.R;
import com.bawei.zhou1.presenter.RegistPresenter;
import com.bawei.zhou1.view.RegistView;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener,RegistView {

    private EditText et_phone;
    private EditText et_pwd;
    private Button regist;
    private RegistPresenter registPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        regist = findViewById(R.id.regist);

        //实例化presenter
        registPresenter = new RegistPresenter(this);
        //点击
        regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        if (phone.isEmpty()||pwd.isEmpty()){
            Toast.makeText(this, "手机号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        registPresenter.sendParameter(phone,pwd);
    }

    @Override
    public void view(String message, String status) {
        if (status.equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "格式不正确", Toast.LENGTH_SHORT).show();
        }
    }
}
