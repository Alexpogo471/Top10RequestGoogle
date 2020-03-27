package ru.pogorelov.top10requestgoogle.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.pogorelov.top10requestgoogle.di.ContextApplicationScope;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ContextApplicationScope
    @Provides
    public Context context(){
        return  context.getApplicationContext();
    }
}
