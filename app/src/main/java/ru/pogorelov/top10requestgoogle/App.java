package ru.pogorelov.top10requestgoogle;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.pogorelov.top10requestgoogle.model.api.GoogleApiRequest;

public class App extends Application {

    private static GoogleApiRequest googleApiRequest;
    private Retrofit retrofit;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        googleApiRequest = retrofit.create(GoogleApiRequest.class);
    }

    public static GoogleApiRequest getApi(){
        return googleApiRequest;
    }




}
