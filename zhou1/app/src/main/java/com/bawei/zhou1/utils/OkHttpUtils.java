package com.bawei.zhou1.utils;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 王艺霏
 * @fileName OkHttpUtils
 * @package com.bawei.zhou1.utils
 **/
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils=null;
    private OkHttpUtils(){

    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                  okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }


    //执行get请求
    public static void doGet(String url,Callback callback){
        //创建拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        //设置拦截器
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建okhttpclient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
