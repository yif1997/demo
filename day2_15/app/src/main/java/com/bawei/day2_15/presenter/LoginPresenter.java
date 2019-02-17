package com.bawei.day2_15.presenter;

import com.bawei.day2_15.model.LoginModel;

/**
 * @author 王艺霏
 * @fileName LoginPresenter
 * @package com.bawei.day2_15.presenter
 **/
public class LoginPresenter {

    private final LoginModel loginModel;

    public LoginPresenter(){
        loginModel = new LoginModel();

    }

    //接收传过来的参数
    public void sendParameter(String phone, String pwd) {
        loginModel.login(phone,pwd);

    }
}
