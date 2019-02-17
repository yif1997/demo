package com.bawei.rikao2_15.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 王艺霏
 * @fileName BaseActivity
 * @package com.bawei.rikao2_15.base
 **/
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        initVIew();
        initData();
    }

    protected abstract int layoutResID();

    protected abstract void initVIew();

    protected abstract void initData();
}
