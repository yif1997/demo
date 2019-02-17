package com.bawei.day2_16.presenter;

import com.bawei.day2_16.MainActivity;
import com.bawei.day2_16.model.ShowModel;
import com.bawei.day2_16.view.ShowView;

import org.json.JSONArray;

/**
 * @author 王艺霏
 * @fileName ShowPresenter
 * @package com.bawei.day2_16.presenter
 **/
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;

    //在狗早方法中实例化model
    public ShowPresenter(ShowView view){
        showModel = new ShowModel();
        //实例化view
        showView = view;
    }

    //从presenter传过来的
    public void related() {
        //传到model做网络请求
        showModel.getGoodsData();
        //接收从model中传过来的result
        showModel.onShowListenter(new ShowModel.OnShowListenter() {
            @Override
            public void onShow(JSONArray result) {
                //把数据给view
                showView.show(result);
            }
        });
    }
}
