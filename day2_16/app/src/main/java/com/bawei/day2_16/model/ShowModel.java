package com.bawei.day2_16.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.day2_16.utils.OkHttpUtils;

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
 * @package com.bawei.day2_16.model
 **/
public class ShowModel {
    private String url = "http://172.17.8.100/small/commodity/v1/bannerShow";

    //定义一个接口
    public interface OnShowListenter{
        //定义一个方法
        void onShow(JSONArray result);
    }
    //声明一个接口
    private OnShowListenter listenter;

    //公共的设置监听的方法
    public void onShowListenter(OnShowListenter listenter){
        this.listenter=listenter;
    }

    //handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json = (String) msg.obj;
                    //Log.i("ccc", "handleMessage: "+json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray result = jsonObject.getJSONArray("result");
                        if (listenter!=null){
                            listenter.onShow(result);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    //做网络请求
    public void getGoodsData() {
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
                message.obj = json;
                handler.sendMessage(message);
            }
        });
    }
}
