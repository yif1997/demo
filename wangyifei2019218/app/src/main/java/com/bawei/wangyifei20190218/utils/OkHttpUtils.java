package com.bawei.wangyifei20190218.utils;

import android.util.Log;
import android.view.LayoutInflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author 王艺霏
 * @fileName OkHttpUtils
 * @package com.bawei.wangyifei20190218.utils
 **/
public class OkHttpUtils {
    //封装get请求
    //单例
    private static OkHttpUtils okHttpUtils;
    private static OkHttpUtils okHttpUtils1;

    private OkHttpUtils(){

    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            //同步锁
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    //实例化okHttpUtils
                    okHttpUtils1 = new OkHttpUtils();
                }
            }
        }
       return okHttpUtils;
    }

    //网络请求
    public static void doGet(String url,Callback callback){
        //拦截
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Request request = new Request.Builder()
                .url("http://365jia.cn/news/api3/365jia/news/headline?page=1")
                .build();

        //创建call
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    //post请求
    public static void doPOst(Callback callback){
        //拦截
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("xxx",message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        //请求体
        RequestBody requestBody = new FormBody.Builder().build();

        Request request = new Request.Builder()
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
