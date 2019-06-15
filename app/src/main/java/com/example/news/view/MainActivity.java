package com.example.news.view;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.Utils.Utils;
import com.example.news.adapter.NewsAdapter;
import com.example.news.model.News;
import com.example.news.viewmodel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ItemClickListener {

    private static final String TAG = MainActivity.class.getName();
    private static NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    public static ArrayList<News> itemList = new ArrayList<News>();
    public static ProgressDialog loading = null;
    public static boolean first_launch = true;
    //  static HashMap<String,ArrayList<Integer>> articleList = new HashMap<String, ArrayList<Integer>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = new ProgressDialog(this);
        loading.setCancelable(true);
        loading.setMessage(Utils.FETCHING_NEWS);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
        final NewsAdapter newsAdapter = new NewsAdapter(itemList, this);
        recyclerView = (RecyclerView) findViewById(R.id.news_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsAdapter);


        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getData();
        noteViewModel.getAllNotes().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                //update RecyclerView
               // loading.dismiss();
                newsAdapter.setNotes(news);
                newsAdapter.notifyDataSetChanged();
            }
        });

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                newsAdapter.notifyDataSetChanged();
//
//            }
//        }, 1000);

    }

    @Override
    public void itemClicked(int position) {
        Intent intent = new Intent(MainActivity.this, ArticleListActivity.class);
        intent.putExtra(Utils.SOURCE_NAME, itemList.get(position).getTitle());
        MainActivity.this.startActivity(intent);

    }

    @Override
    protected void onStop() {
        // first_launch = true;
        super.onStop();
    }
}
