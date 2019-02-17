package com.bawei.zhou1.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author 王艺霏
 * @fileName RegistModel
 * @package com.bawei.zhou1.model
 **/
public class RegistModel {
    //定义一个接口
    public interface OnRegistLisenter{
        void onResult(String message,String status);
    }
    //声明接口
    public OnRegistLisenter onRegistLisenter;

    public void setOnRegistLisenter(OnRegistLisenter onRegistLisenter){
        this.onRegistLisenter=onRegistLisenter;
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
                        String message = jsonObject.getString("message");
                        String status = jsonObject.getString("status");
                        if (onRegistLisenter!=null){
                            onRegistLisenter.onResult(message,status);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    //接收到值从presenter
    public void regist(String phone, String pwd) {
        //网络请求
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();
        //设置post请求方式
        Request request = new Request.Builder()
                .url("http://172.17.8.100/small/user/v1/register")
                .post(requestBody)
                .build();
        //获取call
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            //失败
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
