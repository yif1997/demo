package com.bawei.day2_16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.day2_16.adapter.MyAdapter;
import com.bawei.day2_16.presenter.ShowPresenter;
import com.bawei.day2_16.view.ShowView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements ShowView {

    private RecyclerView rlv;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        rlv = findViewById(R.id.rlv);

        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        rlv.setLayoutManager(linearLayoutManager);

        //采用MVP

        //实例化presenter
        showPresenter = new ShowPresenter(this);

        //定义一个方法传到presenter
        showPresenter.related();
    }

    //view层获取数据
    @Override
    public void show(JSONArray result) {

        //把数据给适配器
        MyAdapter myAdapter = new MyAdapter(result,MainActivity.this);
        rlv.setAdapter(myAdapter);
    }
}
