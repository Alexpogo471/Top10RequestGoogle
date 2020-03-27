package ru.pogorelov.top10requestgoogle.ui.activity;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.pogorelov.top10requestgoogle.App;
import ru.pogorelov.top10requestgoogle.R;
import ru.pogorelov.top10requestgoogle.adapter.MainAdapter;
import ru.pogorelov.top10requestgoogle.adapter.OnItemClickListener;
import ru.pogorelov.top10requestgoogle.model.db.AppDatabase;
import ru.pogorelov.top10requestgoogle.model.entity.Item;
import ru.pogorelov.top10requestgoogle.presenter.MainPresenter;
import ru.pogorelov.top10requestgoogle.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private EditText et_search;
    private Button btn_search;
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<Item> items = new ArrayList<>();
    private AppDatabase database;
    private DividerItemDecoration dividerItemDecoration;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.context = this;

        database = AppDatabase.getInstance(this);

        et_search = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);
        recyclerView = findViewById(R.id.recyclerView);
        getData();
        adapter = new MainAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Item item = adapter.getItems().get(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(item.getUrl()));
                startActivity(browserIntent);
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
            }
        });




        btn_search.setOnClickListener(v -> {
            String request = et_search.getText().toString();
            presenter.loadResponse(request);
        });
    }



    public void getData(){
        LiveData<List<Item>> itemsFromDb = database.getItemDao().getTenLastItems();
        itemsFromDb.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> itemsFromDb) {
                Collections.reverse(itemsFromDb);
                items.addAll(itemsFromDb);
                adapter.notifyDataSetChanged();
            }
        });

    }




    @Override
    public void showResponseGoogle(List<Item> items) {
        adapter.setItems(items);
    }

    public void getAllData(){
        LiveData<List<Item>> itemsFromDb = database.getItemDao().getAllItems();
        itemsFromDb.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> itemsFromDb) {
                items.addAll(itemsFromDb);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
