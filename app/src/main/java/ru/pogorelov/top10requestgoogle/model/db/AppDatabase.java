package ru.pogorelov.top10requestgoogle.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.pogorelov.top10requestgoogle.model.dao.ItemDao;
import ru.pogorelov.top10requestgoogle.model.entity.Item;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase database;

    private static final String DB_NAME = "app.db";

    private static final Object LOCK = new Object();

    public static AppDatabase getInstance(Context context){
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }

    public abstract ItemDao getItemDao();
}
