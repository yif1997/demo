package com.bawei.day2_16.utils;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 王艺霏
 * @fileName OkHttpUtils
 * @package com.bawei.day2_16.utils
 **/
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils = null;
    private static OkHttpUtils okHttpUtils1;

    private OkHttpUtils(){

    }

    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            //同步锁
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    //没有添加
                    okHttpUtils1 = new OkHttpUtils();
                }
            }
        }
        //有就返回
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
        //指定拦截器模式
loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //拦截器
                .addInterceptor(loggingInterceptor)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

























}
