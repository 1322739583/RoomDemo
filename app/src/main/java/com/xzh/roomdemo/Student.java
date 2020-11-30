package com.xzh.roomdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// @Entity 标识这个类用于建表，表名(默认不写，则用类名小写作为表名)
@Entity(tableName = "student")
public class Student {
    // 主键，是否自增长
    @PrimaryKey(autoGenerate = true)
    public int id;
    // 表中字段，表中字段名
    @ColumnInfo(name = "name")
    public String name;
    // 表中字段，默认使用下面字段名age
    @ColumnInfo
    public int age;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}