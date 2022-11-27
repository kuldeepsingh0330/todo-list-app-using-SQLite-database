package com.ransankul.todoapp;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ransankul.todoapp.Adapter.RecyclerViewAdapter;
import com.ransankul.todoapp.Model.ToDo;
import com.ransankul.todoapp.databinding.ActivityAddTaskBinding;
import com.ransankul.todoapp.databinding.ActivityMainBinding;
import com.ransankul.todoapp.utils.dbHelper;

public class addTask extends AppCompatActivity {

    private ActivityAddTaskBinding binding;
    private dbHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper DB= new dbHelper(addTask.this);


         binding.savetaskbtn.setOnClickListener(new View.OnClickListener() {


             @Override
             public void onClick(View view) {

                 String task = binding.ettask.getText().toString();
                 String Sinitial = binding.etinitalvalue.getText().toString();
                 String Sfinal = binding.etfinalvalue.getText().toString();
                 if(task.isEmpty()){
                     binding.ettask.setFocusable(true);
                     binding.ettask.setError("Please enter");
                 }
                 else if(Sinitial.isEmpty()){
                     binding.etinitalvalue.setFocusable(true);
                     binding.etinitalvalue.setError("Please enter");
                 }
                 else if(Sfinal.isEmpty()){
                     binding.etfinalvalue.setFocusable(true);
                     binding.etfinalvalue.setError("Please enter");
                 }else{
                     ToDo savetask = new ToDo();
                     savetask.setTask(task);
                     savetask.setCurrent(Sinitial);
                     savetask.setFinalStatus(Sfinal);
                     DB.addTask(savetask);
                     Intent intent =new Intent(addTask.this,MainActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                     finish();
                 }
             }
         });



    }


}