package com.example.news.rest;

import com.example.news.model.ModelClass;

import retrofit2.Call;
import retrofit2.http.GET;


public interface NewsApi {
    @GET("v2/top-headlines?country=us&apiKey=ddb1491747eb4d36ac9930722d34862a")
    Call<ModelClass> getPosts();
}
