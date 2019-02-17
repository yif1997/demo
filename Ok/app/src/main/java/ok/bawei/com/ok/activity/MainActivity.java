package ok.bawei.com.ok.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import ok.bawei.com.ok.R;
import ok.bawei.com.ok.bean.LoginBean;

public class MainActivity extends AppCompatActivity {
    private String path = "http://172.17.8.100/small/user/v1/login";
    private EditText phone;
    private EditText pwd;
    private Button button1;
    private Button button2;

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what==0){
//                String json = (String) msg.obj;
//                Gson gson = new Gson();
//                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
//                    Toast.makeText(MainActivity.this, "logionBean.getMessage()", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.th//                if (loginBean.getStatus().equals("0000")){is, GoodsActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else if(loginBean.getStatus().equals("1001")){
//                    Toast.makeText(MainActivity.this, "loginBean.getMessage()", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        initData();


    }



    private void initView() {
        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        //点击跳转
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initData() {
    }
}
