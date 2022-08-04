package com.ransankul.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.ransankul.todoapp.Adapter.RecyclerViewAdapter;
import com.ransankul.todoapp.Model.ToDo;
import com.ransankul.todoapp.databinding.ActivityMainBinding;
import com.ransankul.todoapp.utils.dbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerview;
    private RecyclerViewAdapter recyclerViewAdapter;
    private  ArrayList<ToDo> toDoArrayList;
    private ArrayAdapter<String> arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        dbHelper db = new dbHelper(MainActivity.this);

        toDoArrayList = new ArrayList<>();

        List<ToDo> toDoList = db.getAllTask();
        for(ToDo toDo: toDoList){
            toDoArrayList.add(toDo);
        }

        Collections.reverse(toDoArrayList);
        recyclerViewAdapter  = new RecyclerViewAdapter(MainActivity.this, toDoArrayList);
        recyclerview.setAdapter(recyclerViewAdapter);

        Log.d("number", "Bro you have "+ db.getCount()+ " task in your database");


        binding.floatAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,addTask.class);
                startActivity(intent);
            }
        });
    }
}