package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "tb_task";
    private static final String TAG = "SqliteHelper";

    public SqliteHelper(@Nullable Context context) {
        super(context, "db_task", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,is_compeleted BOOLEAN);");
        } catch (SQLException e) {
            Log.e(TAG, "onCreate: " + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<TaskModel> get_tasks() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        List<TaskModel> list_task = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {

                TaskModel taskModel = new TaskModel();
                taskModel.setId(cursor.getLong(0));
                taskModel.setTitle(cursor.getString(1));
                taskModel.setCompleted(cursor.getInt(2)==1);
                list_task.add(taskModel);
            }while (cursor.moveToNext());


        }
        return list_task;
    }

    public long add_task(TaskModel taskModel) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", taskModel.getTitle());
        contentValues.put("is_compeleted", taskModel.isCompleted());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public void update_task() {

    }

    public void delete_task(int id) {

    }

    public void delete_all_task() {

    }

}
