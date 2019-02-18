package com.bawei.wangyifei20190218.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wangyifei20190218.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 王艺霏
 * @fileName MyAdapter
 * @package com.bawei.wangyifei20190218.adapter
 **/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHOlder> {
    Context context;
    JSONArray slider;

    public MyAdapter(Context context, JSONArray slider) {
        this.context = context;
        this.slider = slider;
    }

    @NonNull
    @Override
    public MyViewHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //找布局
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay,null,false);
        MyViewHOlder myViewHOlder = new MyViewHOlder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHOlder myViewHOlder, final int i) {
        try {
            JSONObject jsonObject = slider.getJSONObject(i);
            String title = jsonObject.getString("title");
            String pic = jsonObject.getString("pic");
            myViewHOlder.title.setText("title");
            ImageLoader.getInstance().displayImage(pic,myViewHOlder.img);


            //点击事件
            myViewHOlder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(i);
                }
            });

            myViewHOlder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClick(i);
                    return true;
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return slider.length();
    }

    //自定义ViewHOlder
    public class MyViewHOlder extends RecyclerView.ViewHolder{


        private final ImageView img;
        private final TextView title;

        public MyViewHOlder(@NonNull View itemView) {
            super(itemView);
            //找控件
            title = itemView.findViewById(R.id.title1);
            img = itemView.findViewById(R.id.img);
        }
    }

    //创建一个接口
    public interface OnItemClickListener{
        void onClick(int i);
        void onLongClick(int i);
    }
    //声明一个接口
    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
