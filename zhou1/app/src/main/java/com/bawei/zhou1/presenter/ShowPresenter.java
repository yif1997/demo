package com.bawei.zhou1.presenter;

import com.bawei.zhou1.activity.ShowActivity;
import com.bawei.zhou1.model.ShowModel;
import com.bawei.zhou1.view.ShowVoid;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

/**
 * @author 王艺霏
 * @fileName ShowPresenter
 * @package com.bawei.zhou1.presenter
 **/
public class ShowPresenter<T> {

    private final ShowModel showModel;
    private final ShowVoid showVoid;
    private WeakReference<T> tWeakReference;

    //在构造方法里实例化model
    public ShowPresenter(ShowVoid view){
        showModel = new ShowModel();
        showVoid = view;
    }

    //接收从activity传来的
    public void related() {
        //一个方法传到model
        showModel.getGoods();
        showModel.setOnShowLisenter(new ShowModel.OnShowLisenter() {
            @Override
            public void onShow(JSONArray result) {
                showVoid.view(result);
            }
        });
    }
    //泄露
    public void attachView(T t){
        tWeakReference = new WeakReference<>(t);
    }

    public void detachView(){
        if (tWeakReference.get()!=null){
            tWeakReference.clear();
            tWeakReference=null;
        }
    }
}
