package ru.pogorelov.top10requestgoogle.view;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.pogorelov.top10requestgoogle.R;
import ru.pogorelov.top10requestgoogle.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private EditText et_search;
    private Button btn_search;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_search = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);


        btn_search.setOnClickListener(v -> {
            String request = et_search.getText().toString();
            presenter.loadResponse(request);
        });
    }

    @Override
    public void showResponseGoogle(String request) {

    }
}
