package ru.pogorelov.top10requestgoogle.di.component;

import android.content.Context;

import dagger.Component;
import ru.pogorelov.top10requestgoogle.di.ContextApplicationScope;
import ru.pogorelov.top10requestgoogle.di.module.ContextModule;

@ContextApplicationScope
@Component(modules = ContextModule.class)
public interface ContextComponent {

    Context getContext();

}
