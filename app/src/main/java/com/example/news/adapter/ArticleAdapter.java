package com.example.news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.news.R;
import com.example.news.model.Article;
import com.example.news.view.ArticleListActivity;
import com.example.news.view.MainActivity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> implements Filterable {
    ArrayList<Article> arrayList = new ArrayList<Article>();
    ArrayList<Article> arrayListFull;

    private static ItemClickListener itemclickListener;
    Context context;


    public ArticleAdapter(ArrayList<Article> arrayList, ArticleListActivity mContext) {

        this.arrayList = arrayList;
        arrayListFull = new ArrayList<>(arrayList);
        itemclickListener = mContext;
        context = mContext;

    }

    @Override
    public Filter getFilter() {
        return arrayFilter;
    }

    private Filter arrayFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            ArrayList<Article> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(arrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Article item : arrayListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };


    public interface ItemClickListener {
        void itemClicked(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TextView title;
        TextView author;
        TextView description;

        title = viewHolder.title;
        author = viewHolder.author;
        description = viewHolder.description;
        title.setText(arrayList.get(i).getTitle());
        author.setText("Author : " + arrayList.get(i).getAuthor());
        description.setText(arrayList.get(i).getDescription());

        Glide.with(this.context)
                .load(arrayList.get(i).getUrl())
                .placeholder(R.drawable.load1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imageView);

    }

    public void setNotes(ArrayList<Article> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView imageView;
        TextView author;
        TextView description;



        public ViewHolder(View newsItem) {
            super(newsItem);
            title = newsItem.findViewById(R.id.title);
            author = newsItem.findViewById(R.id.author);
            description = newsItem.findViewById(R.id.description);
            imageView = newsItem.findViewById(R.id.img);

            //imageView = newsItem.findViewById(R.id.image);
//            newsItem.setOnClickListener(new MainActivity.clickEvent(textView));
            newsItem.setOnClickListener(this);
//
        }

        @Override
        public void onClick(View v) {
            itemclickListener.itemClicked(getAdapterPosition());

        }


    }
}
