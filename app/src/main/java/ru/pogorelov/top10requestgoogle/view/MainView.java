package ru.pogorelov.top10requestgoogle.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showResponseGoogle(String request);


}
