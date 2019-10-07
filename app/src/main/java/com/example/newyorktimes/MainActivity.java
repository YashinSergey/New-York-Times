package com.example.newyorktimes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.newyorktimes.network.NewsModel;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);

        updateWeatherData();
    }

    private void updateWeatherData() {
        NetworkService.getNetworkService().getAPI().loadNews(getString(R.string.api_key)).retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<NewsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(NewsModel newsModel) {
                        renderData(newsModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private void renderData(NewsModel model) {
       webView.loadUrl(model.getResults().get(0).getUrl());

    }
}
