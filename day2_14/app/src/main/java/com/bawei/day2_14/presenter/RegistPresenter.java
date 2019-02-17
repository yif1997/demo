package com.bawei.day2_14.presenter;

import com.bawei.day2_14.activity.RegistActivity;
import com.bawei.day2_14.model.RegistModel;

/**
 * @author 王艺霏
 * @fileName RegistPresenter
 * @package com.bawei.day2_14.presenter
 **/
public class RegistPresenter {

    private final RegistModel registModel;

    public RegistPresenter(RegistActivity registActivity){
        registModel = new RegistModel();
    }
    public void sendParameter(String phone, String pwd) {
        registModel.regist(phone,pwd);
    }
}
