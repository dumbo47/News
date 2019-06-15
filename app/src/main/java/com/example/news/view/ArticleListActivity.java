package com.example.news.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.Utils.Utils;
import com.example.news.adapter.ArticleAdapter;
import com.example.news.model.Article;
import com.example.news.model.ModelClass;
import com.example.news.viewmodel.NoteViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleListActivity extends AppCompatActivity implements ArticleAdapter.ItemClickListener {

    private static final String TAG = ArticleListActivity.class.getName();
    RecyclerView recyclerView;
    private static ArrayList<Article> itemList = new ArrayList<Article>();
    ModelClass.Articles articles;
    private String url;
    private String image_url;
    private ArrayList<String> list = new ArrayList<String>();
    private HashMap<String, String> hashMapUrl = new HashMap<String, String>();
    public ArticleAdapter articleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        Bundle bundle = getIntent().getExtras();

        setTitle("Top picks from "+ bundle.getString(Utils.SOURCE_NAME));

        itemList.clear();
        ModelClass res = NoteViewModel.getRes();
        for (int i = 0; i < res.getArticles().size(); i++) {
            articles = res.getArticles().get(i);
            if ((articles.getSource().getName().equals(bundle.getString(Utils.SOURCE_NAME)))) {
                String title = NoteViewModel.getRes().getArticles().get(i).getTitle();
                String author = NoteViewModel.getRes().getArticles().get(i).getAuthor();
                String description = NoteViewModel.getRes().getArticles().get(i).getDescription();
                list.add(title);
                url = NoteViewModel.getRes().getArticles().get(i).getUrl();
                image_url = NoteViewModel.getRes().getArticles().get(i).getUrlToImage();
                hashMapUrl.put(title, url);
                itemList.add(new Article(title, author, description, image_url));

            }

        }


        articleAdapter = new ArticleAdapter(itemList, this);
        recyclerView = (RecyclerView) findViewById(R.id.news_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(articleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.title_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                articleAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void itemClicked(int position) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
//            browserIntent.setData(Uri.parse(hashMap.get(title)));
//            startActivity(browserIntent);
        Intent intent = new Intent(ArticleListActivity.this, WebViewActivity.class);
        intent.putExtra(Utils.URL, hashMapUrl.get(itemList.get(position).getTitle()));
        startActivity(intent);


    }
}
