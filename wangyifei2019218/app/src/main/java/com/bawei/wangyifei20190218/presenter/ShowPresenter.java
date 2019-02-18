package com.bawei.wangyifei20190218.presenter;

import com.bawei.wangyifei20190218.activity.MainActivity;
import com.bawei.wangyifei20190218.model.ShowModel;
import com.bawei.wangyifei20190218.view.ShowView;

import org.json.JSONArray;

import java.lang.ref.WeakReference;

/**
 * @author 王艺霏
 * @fileName ShowPresenter
 * @package com.bawei.wangyifei20190218.presenter
 **/
public class ShowPresenter {

    private final ShowModel showModel;
    private final ShowView showView;
    private WeakReference tWeakReference;

    //在构造方法里实例化model,view
    public ShowPresenter(ShowView view){
        showModel = new ShowModel();
        showView = view;
    }
    public void getGoods() {
        //穿到model层
        showModel.show();
        showModel.setOnShowLIstener(new ShowModel.OnShowLIstener() {
            @Override
            public void onShow(JSONArray slider) {
                showView.view(slider);
            }
        });
    }

    public void attach(T t){
        tWeakReference = new WeakReference<>(t);
    }

    public void detchView(){
        if (tWeakReference==null){
            tWeakReference.clear();
            tWeakReference=null;
        }
    }

}
