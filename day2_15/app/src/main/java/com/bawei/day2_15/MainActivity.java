package com.bawei.day2_15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.day2_15.presenter.LoginPresenter;
import com.bawei.day2_15.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_phone;
    private EditText et_pwd;
    private Button button;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        button = findViewById(R.id.button);


        //实例化presenter
        loginPresenter = new LoginPresenter();
        //点击事件
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //先获取输入框的值
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();

        //判断手机号格式
        boolean mobileNO = Utils.isMobileNO(phone);
        if (!mobileNO){
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.length()<3){
            Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }


        //从activity传参到model
        loginPresenter.sendParameter(phone,pwd);
    }
}
