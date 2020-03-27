package ru.pogorelov.top10requestgoogle.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.pogorelov.top10requestgoogle.model.entity.Item;

@Dao
public interface ItemDao {

    @Insert
    void insertAll(List<Item> items);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM item")
    void deleteAllItems();

    @Query("SELECT * FROM item")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM item ORDER BY id DESC LIMIT 10")
    LiveData<List<Item>> getTenLastItems();


}
