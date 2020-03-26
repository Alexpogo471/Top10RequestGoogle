package ru.pogorelov.top10requestgoogle.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.pogorelov.top10requestgoogle.model.entity.Item;

@Dao
public interface ItemDao {

    @Insert
    void inserAll(Item... items);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM item")
    List<Item> getAllItems();


}
