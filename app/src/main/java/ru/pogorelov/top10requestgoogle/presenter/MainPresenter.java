package ru.pogorelov.top10requestgoogle.presenter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pogorelov.top10requestgoogle.App;
import ru.pogorelov.top10requestgoogle.model.db.AppDatabase;
import ru.pogorelov.top10requestgoogle.model.entity.Item;
import ru.pogorelov.top10requestgoogle.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private String API_KEY = "AIzaSyArrgrpZssR7HnAfl93iBsABDUMM-s-A64";

    private String CX = "002712518364234490617:wgke6gh6inu";

    private AppDatabase database;

    @Inject
    Context context;


//
    // https://www.googleapis.com/customsearch/v1?key=AIzaSyArrgrpZssR7HnAfl93iBsABDUMM-s-A64&cx=002712518364234490617:wgke6gh6inu&q=stalin

    public void loadResponse(String request) {
        App.getApi().getData(API_KEY, CX, request).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Log.i("testAPI", response.body().toString());
                String data = response.body().toString();
                List<Item> items = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String title = item.getString("title");
                        String link = item.getString("link");
                        String description = item.getString("snippet");

                        items.add(new Item(title,link,description));
                        getViewState().showResponseGoogle(items);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Log.i("testAPI",items.toString());
                database = AppDatabase.getInstance(App.context);
                database.getItemDao().insertAll(items);
                Log.i("testAPI",database.getItemDao().getAllItems().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("testAPI", "Ошибка "+t.toString());
            }
        });
    }



}
