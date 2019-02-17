package com.bawei.zhou1.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author 王艺霏
 * @fileName LoginModel
 * @package com.bawei.zhou1.model
 **/
public class LoginModel {
    //创建接口
    public interface OnLoginLisenter{
        void onResult(String status);
    }
    //声明接口
    public OnLoginLisenter onLoginLisenter;

    //
    public void setOnLoginLisenter(OnLoginLisenter onLoginLisenter){
        this.onLoginLisenter=onLoginLisenter;
    }
    //handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json = (String) msg.obj;
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String status = jsonObject.getString("status");
                        if (onLoginLisenter!=null){
                            onLoginLisenter.onResult(status);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    //接收到值 从presenter
    public void login(String phone, String pwd) {
        //网络请求
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .build();

        //设置请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();

        //post请求
        Request request = new Request.Builder()
                .url("http://172.17.8.100/small/user/v1/login")
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);

        //执行异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("xxx",json);
                Message message = new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);
            }
        });
    }
}
