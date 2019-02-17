package com.bawei.day2_16.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.day2_16.MainActivity;
import com.bawei.day2_16.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 王艺霏
 * @fileName MyAdapter
 * @package com.bawei.day2_16.adapter
 **/
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    JSONArray result;
    Context context;
    public MyAdapter(JSONArray result, Context context) {
        this.result=result;
        this.context=context;
    }

    //加载条目布局
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lay,null,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            JSONObject jsonObject = result.getJSONObject(i);
            String title = jsonObject.getString("title");
            String pic = jsonObject.getString("imageUrl");

            myViewHolder.title.setText(title);
            ImageLoader.getInstance().displayImage(pic,myViewHolder.img);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return result.length();
    }


    //自定义ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlle);
            img = itemView.findViewById(R.id.img);
        }
    }
}
