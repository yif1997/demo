package com.bawei.zhou1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.zhou1.R;
import com.bawei.zhou1.presenter.LoginPresenter;
import com.bawei.zhou1.utils.Utils;
import com.bawei.zhou1.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,LoginView {

    private EditText et_phone;
    private EditText et_pwd;
    private Button login;
    private Button regist;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);

        //实例化presenter
        loginPresenter = new LoginPresenter(this);
        //设置点击事件
        login.setOnClickListener(this);
        regist.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                boolean mobileNO = Utils.isMobileNO(phone);
                if (!mobileNO){
                    Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                }
                if (pwd.length()<3){
                    Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
                }
                loginPresenter.sendParameter(phone,pwd);
                break;
            case R.id.regist:
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void view(String status) {
        if (status.equals("0000")){
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
