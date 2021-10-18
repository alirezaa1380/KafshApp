package com.example.kafshapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RoomDao {

    @Insert
    void AddFromDatabaseBasket(Post post);

    @Query("SELECT * FROM Post ORDER BY id DESC")
    Single<List<Post>> GetDataFromDatabaseBasket();
}
