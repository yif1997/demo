package com.bawei.zhou1.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.zhou1.utils.OkHttpUtils;

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
 * @package com.bawei.zhou1.model
 **/
public class ShowModel {
    //创建接口
    public interface  OnShowLisenter{
        void onShow(JSONArray result);
    }
    //声明接口
    public OnShowLisenter onShowLisenter;
    //set接口
    public void setOnShowLisenter(OnShowLisenter onShowLisenter){
        this.onShowLisenter=onShowLisenter;
    }
    private String url ="http://172.17.8.100/small/commodity/v1/bannerShow";
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
                        JSONArray result = jsonObject.getJSONArray("result");
                        if (onShowLisenter!=null){
                            onShowLisenter.onShow(result);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
    //从p传来的
    public void getGoods() {
        OkHttpUtils.getInstance().doGet(url,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到响应码
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
