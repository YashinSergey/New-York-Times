package com.example.newyorktimes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newyorktimes.network.NewsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        updateWeatherData();
    }

    private void updateWeatherData() {
        NetworkService
                .getNetworkService()
                .getAPI()
                .loadNews(getString(R.string.api_key))
                .enqueue(new Callback<NewsModel>() {
                    @Override
                    public void onResponse(@NonNull Call<NewsModel> call,
                                           @NonNull Response<NewsModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            renderData(response.body());
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Network error",
                                Toast.LENGTH_SHORT).show();
                        Log.e("RESPONSE", "Network error");
                    }
                });
    }

    private void renderData(NewsModel model) {
        for (int i = 0; i < model.getResults().size(); i++) {
            textView.append(model.getResults().get(i).getTitle() + "\n");
        }
    }
}
