package com.example.tave0915;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.ViewHolder> {

    private ArrayList<MainCategoryCard> CardList = null;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        CardView cardView;
        ViewHolder(View itemView) {
            super(itemView) ;
            tv_title = itemView.findViewById(R.id.title_category);
            cardView = itemView.findViewById(R.id.CV_category);
        }
    }

    public MainRVAdapter(ArrayList<MainCategoryCard> list, Context mContext) {
        this.CardList = list ;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MainRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.category_select_component, parent, false) ;
        MainRVAdapter.ViewHolder vh = new MainRVAdapter.ViewHolder(view) ;
        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRVAdapter.ViewHolder holder, int position) {
        MainCategoryCard categoryCard = CardList.get(position);
        holder.tv_title.setText(categoryCard.getCategory_title());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(v.getContext(), ListActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CardList.size();
    }
}
