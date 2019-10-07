package com.example.newyorktimes;

import com.example.newyorktimes.network.NewsModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRestApi {

    @GET("svc/news/v3/content/nyt/all.json{api-key}")
    Single<NewsModel> loadNews(@Query("api-key") String key);
}
