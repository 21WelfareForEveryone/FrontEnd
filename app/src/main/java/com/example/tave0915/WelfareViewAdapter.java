package com.example.tave0915;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WelfareViewAdapter extends RecyclerView.Adapter<WelfareViewAdapter.ViewHolder> {

    private ArrayList<WelfareInfoComponent> CardList = null;
//    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_summary;
        CardView cv_welfare_info;
        ImageView welfare_img;

        ViewHolder(View itemView) {
            super(itemView) ;
            // 뷰 객체에 대한 참조. (hold strong reference)
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_summary = itemView.findViewById(R.id.tv_summary);
            cv_welfare_info = itemView.findViewById(R.id.CV_welfare_info);
            welfare_img = itemView.findViewById(R.id.welfare_img);
        }
    }

    public WelfareViewAdapter(ArrayList<WelfareInfoComponent> list) {
        this.CardList = list ;
    }

    @Override
    public WelfareViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.card_component, parent, false) ;
        WelfareViewAdapter.ViewHolder vh = new WelfareViewAdapter.ViewHolder(view) ;
        return vh ;
    }

    @Override
    public void onBindViewHolder(WelfareViewAdapter.ViewHolder holder, int position) {

        WelfareInfoComponent info = CardList.get(position);
        int  welfare_id = info.getWelfare_id();
        try{
            //holder.welfare_img.setImageResource(R.drawable.img_category_00);
            holder.welfare_img.setImageResource(R.drawable.ic_user_profile);
        }
        catch(Exception err){
            holder.welfare_img.setImageResource(R.drawable.ic_user_profile);
            err.printStackTrace();
        }
        holder.tv_title.setText(info.getTitle());
        holder.tv_summary.setText(info.getSummary());
        holder.cv_welfare_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Welfare CardView click!","true");
                Bundle bundle = new Bundle();
                bundle.putInt("welfare_id", welfare_id);
                Context context = v.getContext();
                try{
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
                catch(Exception err){
                    Log.v("WelfareInfo to DetailActivity intent process","error");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return CardList.size();
    }
}
