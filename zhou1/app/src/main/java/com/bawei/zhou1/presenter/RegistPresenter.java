package com.bawei.zhou1.presenter;

import com.bawei.zhou1.activity.RegistActivity;
import com.bawei.zhou1.model.RegistModel;
import com.bawei.zhou1.view.RegistView;

/**
 * @author 王艺霏
 * @fileName RegistPresenter
 * @package com.bawei.zhou1.presenter
 **/
public class RegistPresenter {

    private final RegistModel registModel;
    private final RegistView registView;

    //在构造方法里实例化model
    public RegistPresenter(RegistView view){
        registModel = new RegistModel();
        registView = view;
    }

    //接收从activity传过来的值
    public void sendParameter(String phone, String pwd) {
        //把值传到model
        registModel.regist(phone,pwd);
        registModel.setOnRegistLisenter(new RegistModel.OnRegistLisenter() {
            @Override
            public void onResult(String message, String status) {
                registView.view(message,status);
            }
        });
    }
}
