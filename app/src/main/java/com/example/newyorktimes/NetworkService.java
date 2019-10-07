package com.example.newyorktimes;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService networkService = null;
    private IRestApi api;

    private NetworkService() {
        api = createAdapter();
    }

    public static NetworkService getNetworkService() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    private IRestApi createAdapter() {
        Retrofit adapter = new Retrofit.Builder().baseUrl("https://api.nytimes.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(IRestApi.class);
    }

    public IRestApi getAPI() {
        return api;
    }
}
