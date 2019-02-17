package ok.bawei.com.ok.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import ok.bawei.com.ok.R;
import ok.bawei.com.ok.bean.RegistBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {
    private String path = "http://172.17.8.100/small/user/v1/register";
    private EditText phone;

    private EditText pwd;
    private Button button;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String json = (String) msg.obj;
                Gson gson = new Gson();
                RegistBean registBean = gson.fromJson(json, RegistBean.class);
                if (registBean.getStatus().equals("0000")){
                    Toast.makeText(SecondActivity.this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (registBean.getStatus().equals("1001")){
                    Toast.makeText(SecondActivity.this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //初始化控件
        initView();
        initData();
    }



    private void initView() {
        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        button = findViewById(R.id.button);
    }

    private void initData() {
        //立即注册
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                String phones = phone.getText().toString().trim();
                String pwds = pwd.getText().toString().trim();
                //判断
                if (phones.isEmpty()||pwds.isEmpty()){
                    Toast.makeText(SecondActivity.this, "手机号密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (phones.length()!=11){
                        Toast.makeText(SecondActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    }
                    else if (pwds.length()<3){
                        Toast.makeText(SecondActivity.this, "密码格式不正确", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //实例化OkHttpClient
                        OkHttpClient okHttpClient = new OkHttpClient();
                        //请求体
                        RequestBody formBody = new FormBody.Builder()
                                .add("phone",phones)
                                .add("pwd",pwds)
                                .build();
                        //配置请求方式
                        Request request = new Request.Builder()
                                .post(formBody)
                                .url(path)
                                .build();
                        //执行异步
                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                //获得到数据
                                String json = response.body().string();
                                Log.i("xxxx","onRespone:"+json);
                                Message message = new Message();
                                message.what=0;
                                message.obj=json;
                                //发送消息
                                handler.sendMessage(message);
                            }
                        });
                    }
                }
            }
        });
    }
}
