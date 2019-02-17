package com.bawei.rikao2_14.adapater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.rikao2_14.R;
import com.bawei.rikao2_14.bean.Step;

import java.util.ArrayList;

/**
 * @author 王艺霏
 * @fileName MyAdapter
 * @package com.bawei.rikao2_14.adapater
 **/
public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Step> list;

    public MyAdapter(Context context, ArrayList<Step> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = View.inflate(context,R.layout.lay,null);
            TextView name = convertView.findViewById(R.id.name);
            ImageView img = convertView.findViewById(R.id.img);
            TextView title = convertView.findViewById(R.id.ttile);
        }
        return null;
    }
    class ViewHolder{
        public TextView t_name;
        public ImageView t_img;
        public TextView t_title;
    }
}
