package com.ransankul.todoapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ransankul.todoapp.Model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "TODO_DATABASE";




    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "create table TODO_TABLE (id integer primary key autoincrement , task text , initial text " +
                ", final text) ";;
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS TODO_TABLE" );
        onCreate(db);
    }


    public void addTask(ToDo todo) {
         db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("task", todo.getTask());
        values.put("initial", todo.getCurrent());
        values.put("final", todo.getFinalStatus());

        db.insert("TODO_TABLE", null, values);
        Log.d("uiop", "success");

        db.close();
    }

    public int updateStatus(String initial,ToDo toDo) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("initial", initial);
        return db.update("TODO_TABLE", values, "id"+ "=?",
                new String[]{String.valueOf(toDo.getId())});

    }

    public void deleteTask(int id) {
        db = this.getWritableDatabase();
        db.delete("TODO_TABLE", "id" +"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<ToDo> getAllTask(){
        List<ToDo> toDoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + "TODO_TABLE";
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if (cursor.moveToFirst()) {
            do {
                ToDo task = new ToDo();
                task.setId(Integer.parseInt(cursor.getString(0)));
                task.setTask(cursor.getString(1));
                task.setCurrent(cursor.getString(2));
                task.setFinalStatus(cursor.getString(3));
                toDoList.add(task);

            } while (cursor.moveToNext());
        }
        return toDoList;
    }

    public int getCount(){
        String query = "SELECT  * FROM " + "TODO_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();
    }
}
