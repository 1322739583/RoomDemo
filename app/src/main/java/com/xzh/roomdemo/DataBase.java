package com.xzh.roomdemo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
}