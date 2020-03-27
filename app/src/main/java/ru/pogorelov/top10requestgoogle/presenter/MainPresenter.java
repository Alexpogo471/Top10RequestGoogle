package ru.pogorelov.top10requestgoogle.presenter;

import android.util.Log;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.pogorelov.top10requestgoogle.App;
import ru.pogorelov.top10requestgoogle.BuildConfig;
import ru.pogorelov.top10requestgoogle.model.db.AppDatabase;
import ru.pogorelov.top10requestgoogle.model.entity.Item;
import ru.pogorelov.top10requestgoogle.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private String API_KEY = BuildConfig.KEY;

    private String CX = BuildConfig.CX;

    private AppDatabase database;

    public void loadResponse(String request) {
        App.getApi().getData(API_KEY, CX, request).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
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
                database = AppDatabase.getInstance(App.context);
                database.getItemDao().insertAll(items);
                Log.i("testAPI",database.getItemDao().getAllItems().toString());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.i("testAPI", "Error "+t.toString());
                getViewState().showError(t);
            }
        });
    }



}
