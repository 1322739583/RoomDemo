package com.xzh.roomdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Room-";
    private StudentDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase dataBase = Room.databaseBuilder(getApplicationContext(), DataBase.class,
                "room").allowMainThreadQueries().build();
        dao = dataBase.studentDao();
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            Log.d(TAG, "onRequestPermissionsResult: " + requestCode);
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult-需要开启存储权限");
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 在Activity中所有对数据库的操作(增、删、改、查)都不可以在主线程中进行,除非在数据库的Builder上调用了allowMainThreadQueries(),
     * 或者所有的操作都在子线程中完成,否则程序会崩溃报以下错误
     * Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
     *
     * @param view
     */
    public void queryAll(View view) {
        List<Student> students = dao.getAll();
        Log.d(TAG, "queryAll: " + students);
    }

    public void insert(View view) {
        Student student = new Student();
        student.age = 18;
        student.name = "小明";
        dao.insert(student);
        Log.d(TAG, "insert:" + student.toString());
    }

    /**
     * 删除必须指定id，否则删除不成功(id不存在也是删除不成功的)
     *
     * @param view
     */
    public void delete(View view) {
        Student student = new Student();
        student.age = 18;
        student.name = "小明";
        student.id = 1;
        dao.delete(student);
        Log.d(TAG, "insert:" + student.toString());
    }

    /**
     * 更新必须指定id，否则不成功(id不存在也是更新不成功的)
     *
     * @param view
     */
    public void update(View view) {
        Student student = new Student();
        student.age = 18;
        student.name = "小明-1";
        student.id = 1;
        dao.update(student);
        Log.d(TAG, "insert:" + student.toString());
    }

    public void queryByName(View view) {
        Log.d(TAG, "queryByName: " + dao.findByName("小明"));
    }

    public void queryByIds(View view) {
        Log.d(TAG, "queryByName: " + dao.findByIds(new int[]{1, 2, 3}));
    }
}
