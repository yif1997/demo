package com.bawei.wangyifei20190218.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.wangyifei20190218.utils.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author 王艺霏
 * @fileName ShowModel
 * @package com.bawei.wangyifei20190218.model
 **/
public class ShowModel {
    //创建接口
    public interface OnShowLIstener{
        void onShow(JSONArray slider);
    }
    //声明接口
    public OnShowLIstener onShowLIstener;

    //set
    public void setOnShowLIstener(OnShowLIstener onShowLIstener){
        this.onShowLIstener = onShowLIstener;
    }
    private String url = "http://365jia.cn/news/api3/365jia/news/headline?page=1";
    //handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json = (String) msg.obj;
                    //解析数据
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject extras = data.getJSONObject("extras");
                        JSONArray slider = extras.getJSONArray("slider");

                        if (onShowLIstener!=null){
                            onShowLIstener.onShow(slider);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    //接收从presenter传过来的
    public void show() {
        //调用OkHttpUtils
       OkHttpUtils.getInstance().doGet(url, new Callback() {
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
               //发送数据
               handler.sendMessage(message);
           }
       });
    }
}
