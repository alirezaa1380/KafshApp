package com.example.kafshapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//base Database Room
@Database(version = 1,exportSchema = false,entities = {Post.class})
public  abstract class AppDatabse extends RoomDatabase {
    public static AppDatabse appDatabse;

    public static AppDatabse getAppDatabse(Context context) {
        if (appDatabse==null){
            appDatabse= Room.databaseBuilder(context.getApplicationContext(),AppDatabse.class,"db_kafsh")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabse;
    }
    public abstract RoomDao roomDao();
}
