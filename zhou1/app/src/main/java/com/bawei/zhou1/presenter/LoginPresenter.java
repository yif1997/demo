package com.bawei.zhou1.presenter;

import com.bawei.zhou1.activity.MainActivity;
import com.bawei.zhou1.model.LoginModel;
import com.bawei.zhou1.view.LoginView;

/**
 * @author 王艺霏
 * @fileName LoginPresenter
 * @package com.bawei.zhou1.presenter
 **/
public class LoginPresenter {

    private final LoginModel loginModel;
    private final LoginView loginView;

    //在构造方法里实例化model,view
    public LoginPresenter(LoginView view){
        loginModel = new LoginModel();
        loginView = view;
    }
    //接收从activity传来的值
    public void sendParameter(String phone, String pwd) {
        //传参到model
        loginModel.login(phone,pwd);
        loginModel.setOnLoginLisenter(new LoginModel.OnLoginLisenter() {
            @Override
            public void onResult(String status) {
                loginView.view(status);
            }
        });

    }
}
