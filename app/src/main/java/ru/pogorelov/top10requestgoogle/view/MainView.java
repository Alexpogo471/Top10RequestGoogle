package ru.pogorelov.top10requestgoogle.view;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.pogorelov.top10requestgoogle.model.entity.Item;

public interface MainView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showResponseGoogle(List<Item> items);


}
