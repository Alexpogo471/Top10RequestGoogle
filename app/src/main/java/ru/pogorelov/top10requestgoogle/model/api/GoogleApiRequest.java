package ru.pogorelov.top10requestgoogle.model.api;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.pogorelov.top10requestgoogle.model.Item;

public interface GoogleApiRequest {

    @GET("customsearch/v1")
    Call<JsonObject> getData(@Query("key") String apiKey, @Query("cx") String cx,
                             @Query("q") String request);
}
