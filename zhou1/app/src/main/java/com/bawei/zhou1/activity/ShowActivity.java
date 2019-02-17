package com.bawei.zhou1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bawei.zhou1.R;
import com.bawei.zhou1.adapter.MyAdapter;
import com.bawei.zhou1.presenter.ShowPresenter;
import com.bawei.zhou1.view.ShowVoid;

import org.json.JSONArray;

public class ShowActivity extends AppCompatActivity implements ShowVoid {

    private RecyclerView rel;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //找控件
        rel = findViewById(R.id.rel);

        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rel.setLayoutManager(linearLayoutManager);

        //MVP
        //实例化presenter
        showPresenter = new ShowPresenter(this);

        //关联p层
        showPresenter.related();


    }

    @Override
    public void view(JSONArray result) {

        MyAdapter myAdapter = new MyAdapter(this,result);
        rel.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int i) {
                Toast.makeText(ShowActivity.this, "第"+i+"个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongClick(int i) {
                Toast.makeText(ShowActivity.this, "长按事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //销毁

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.detachView();
        Log.i("xxx","销毁了");
    }
}
