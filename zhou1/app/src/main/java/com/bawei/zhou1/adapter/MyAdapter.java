package com.bawei.zhou1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhou1.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 王艺霏
 * @fileName MyAdapter
 * @package com.bawei.zhou1.adapter
 **/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    JSONArray result;
    public MyAdapter(Context context,JSONArray result){
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //带入布局 实例化viewholder
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay,null,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        //解析数据并赋值
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String title = jsonObject.getString("title");
            String img = jsonObject.getString("imageUrl");
            //赋值
            myViewHolder.name.setText(title);
            ImageLoader.getInstance().displayImage(img,myViewHolder.img);

            //点击事件
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnClick(i);
                }
            });
            //长按事件
            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.OnLongClick(i);
                    return true;
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return result.length();
    }

    //自定义一个内部的ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.img);
        }
    }

    //定义接口
    public interface OnItemClickListener{
        void OnClick(int i);
        void OnLongClick(int i);
    }
    //声明接口
    private OnItemClickListener onItemClickListener;

    //set
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}



