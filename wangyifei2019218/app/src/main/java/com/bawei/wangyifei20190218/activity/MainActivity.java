package com.bawei.wangyifei20190218.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.bawei.wangyifei20190218.R;
import com.bawei.wangyifei20190218.adapter.MyAdapter;
import com.bawei.wangyifei20190218.presenter.ShowPresenter;
import com.bawei.wangyifei20190218.view.ShowView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements ShowView {


    private RecyclerView rel;
    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        rel = findViewById(R.id.rel);

        //管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rel.setLayoutManager(linearLayoutManager);

        //实例化presenter
        showPresenter = new ShowPresenter(this);

        //进入到presenter
        showPresenter.getGoods();
    }

    @Override
    public void view(JSONArray slider) {
        MyAdapter myAdapter = new MyAdapter(this, slider);
        rel.setAdapter(myAdapter);

        //点击事件
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击事件啦", Toast.LENGTH_SHORT).show();
            }
        });

        //长按事件
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "长按事件啦", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.detchView();
        
    }
}
