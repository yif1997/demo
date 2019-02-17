package com.bawei.rikao2_14.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.bawei.rikao2_14.R;
import com.bawei.rikao2_14.bean.JsonBean;
import com.bawei.rikao2_14.bean.Step;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private int page = 1;
    private PullToRefreshListView pul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pul = findViewById(R.id.pul);
        pul.setMode(PullToRefreshBase.Mode.BOTH);
        pul.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page =1;
                getNetData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pul.onRefreshComplete();
                    }
                },2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getNetData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pul.onRefreshComplete();
                    }
                },2000);
            }
        });
        getNetData();
    }

    private void getNetData() {
    }

    private class MyAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String path = "http://www.xieast.com/api/news/news.php?page=2";
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                if (connection.getResponseCode()==200){
                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String str = "";
                    while ((str=reader.readLine())!=null){
                        builder.append(str);
                    }
                    return builder.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson = new Gson();
            JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
            ArrayList<Step> data = jsonBean.getData();

        }
    }
}
