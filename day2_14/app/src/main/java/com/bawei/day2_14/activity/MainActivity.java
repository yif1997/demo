package com.bawei.day2_14.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.day2_14.R;
import com.bawei.day2_14.presenter.LoginPresenter;
import com.bawei.day2_14.utils.Utils;
import com.bawei.day2_14.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,LoginView {

    private EditText et_phone;
    private EditText et_pwd;
    private Button button;
    private TextView tv_name;
    private LoginPresenter presenter;
    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        button = findViewById(R.id.button);
        tv_name = findViewById(R.id.tv_name);
        regist = findViewById(R.id.regist);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //2.进入到presenter  实例presenter
        presenter = new LoginPresenter(this);

        //设置点击事件
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //1.先获取输入框的值
        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();
        boolean mobileNO = Utils.isMobileNO(phone);
        if (!mobileNO){
            Toast.makeText(this, "手机号格式不对", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.length()<3){
            Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }


        //3.传参到presenter
        presenter.sendParameter(phone,pwd);

    }

    @Override
    public void view(String status) {

        if (status.equals("0000")){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
