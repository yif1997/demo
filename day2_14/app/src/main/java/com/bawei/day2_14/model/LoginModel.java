package com.bawei.day2_14.model;

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
 * @package com.bawei.day2_14.model
 **/

//model里写网络请求
public class LoginModel {
    //定义接口
    public interface OnLoginLisenter{
        void onResult(String status);
    }
    //声明接口
    public OnLoginLisenter loginLisenter;

    //提供一个公共的设置监听的方法
    public void setOnLoginLisenter(OnLoginLisenter loginLisenter){
        this.loginLisenter = loginLisenter;
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
                        if (loginLisenter!=null){
                            //回调数据
                            loginLisenter.onResult(status);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
    //5.从presenter中传过来的值
    public void login(String phone, String pwd) {
        //创建网络请求对象
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000,TimeUnit.MILLISECONDS)
                .build();

        //创建请求体requestbody并封装请求参数
        RequestBody requestBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();


        //创建post请求
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

            //此方法在子线程
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("xxxx",json);

                Message message = new Message();
                message.what=0;
                message.obj=json;
                //发送消息
                handler.sendMessage(message);
            }
        });


    }
}
