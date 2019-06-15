package com.example.news.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.view.MainActivity;
import com.example.news.R;
import com.example.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> arrayList = new ArrayList<>();
    public static ItemClickListener itemclickListener;


    public NewsAdapter(ArrayList<News> arrayList, MainActivity mContext) {
        this.arrayList.clear();
        this.arrayList = arrayList;
        itemclickListener = mContext;

    }

    public interface ItemClickListener {
        void itemClicked(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView textView;
        textView = viewHolder.textView;
        textView.setText(arrayList.get(i).getTitle());

    }

    public void setNotes(List<News> arrayList) {
        this.arrayList.clear();
        for(int i =0;i<arrayList.size();i++){
            this.arrayList.add(arrayList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(View newsItem) {
            super(newsItem);
            textView = newsItem.findViewById(R.id.title);
            newsItem.setOnClickListener(this);
//
        }

        @Override
        public void onClick(View v) {
            itemclickListener.itemClicked(getAdapterPosition());
        }
    }
}
