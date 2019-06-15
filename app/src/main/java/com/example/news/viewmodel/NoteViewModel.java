package com.example.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import com.example.news.Utils.Utils;
import com.example.news.model.News;
import com.example.news.NoteRepository;
import com.example.news.model.ModelClass;
import com.example.news.rest.NewsApi;
import com.example.news.rest.RetroClient;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.ContentValues.TAG;
import static com.example.news.view.MainActivity.first_launch;
import static com.example.news.view.MainActivity.itemList;
import static com.example.news.view.MainActivity.loading;

public class NoteViewModel extends AndroidViewModel {

    public static final String TAG = NoteViewModel.class.getName();

    NoteRepository noteRepository;
    LiveData<List<News>> allNotes;

    Context mcontext;
    public static ModelClass res;
    News news;


    public static ModelClass getRes() {
        return res;
    }

    public NoteViewModel(@NonNull Application application) {
        super(application);
        mcontext = application;
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }


    public void getData() {
        Retrofit retrofit = RetroClient.getClient();
        NewsApi newsApi = retrofit.create(NewsApi.class);
        Call<ModelClass> call = newsApi.getPosts();
        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mcontext, "fail" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onResponse: " + "positive");
                    res = response.body();
                    updateUI(res);

                }

            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                Log.d(TAG, "onResponse: " + "onFailure");

            }
        });

    }

    //
    private void updateUI(ModelClass res) {
        ArrayList<String> list = new ArrayList<String>();
        itemList.clear();
        list.clear();
        for (int i = 0; i < res.getArticles().size(); i++) {
            ModelClass.Articles articles = res.getArticles().get(i);

            if (!list.contains(articles.getSource().getName())) {
                list.add(articles.getSource().getName());
            }
        }
        if(list.size()!=0){
            loading.dismiss();
        }
        Log.d("bhoo", first_launch+" "+list.size());
        for (int i = 0; i < list.size(); i++) {
            news = new News(list.get(i));
            if (first_launch) {
                insert(news);
            }
            itemList.add(news);
        }
        first_launch = false;
    }


    public void insert(News news) {
        noteRepository.insert(news);
    }

    public void update(News note) {
        noteRepository.update(note);
    }

//    public void delete(Note note) {
//        noteRepository.delete(note);
//    }


    public LiveData<List<News>> getAllNotes() {
        return allNotes;
    }
}
