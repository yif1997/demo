package com.bawei.day2_14.presenter;

import com.bawei.day2_14.model.LoginModel;
import com.bawei.day2_14.view.LoginView;

/**
 * @author 王艺霏
 * @fileName LoginPresenter
 * @package com.bawei.day2_14.presenter
 **/
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    //4.在构造方法中实例化model对象
    public LoginPresenter(LoginView view){
        loginModel = new LoginModel();
        loginView = view;

    }

    //穿过来的参数
    public void sendParameter(String phone, String pwd) {
        //5.传值到model
        loginModel.login(phone,pwd);
        loginModel.setOnLoginLisenter(new LoginModel.OnLoginLisenter() {
            @Override
            public void onResult(String status) {
                loginView.view(status);
            }
        });
    }




    //接受从model回调过来的数据给View

}
