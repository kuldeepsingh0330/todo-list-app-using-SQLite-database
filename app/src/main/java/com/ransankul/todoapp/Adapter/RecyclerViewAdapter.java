package com.ransankul.todoapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ransankul.todoapp.MainActivity;
import com.ransankul.todoapp.Model.ToDo;
import com.ransankul.todoapp.R;
import com.ransankul.todoapp.addTask;
import com.ransankul.todoapp.utils.dbHelper;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<ToDo> toDoList;

    public RecyclerViewAdapter(Context context, List<ToDo> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskdisplat, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
          ToDo todo = toDoList.get(position);

        dbHelper DB= new dbHelper(context.getApplicationContext());;
         holder.task.setText(todo.getTask());
         holder.current.setText(todo.getCurrent());
         holder.finalStatus.setText(todo.getFinalStatus());
         holder.progressBar.setProgress(Integer.parseInt(todo.getCurrent()));
         holder.progressBar.setMax(Integer.parseInt(todo.getFinalStatus()));


         holder.taskcard.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(toDoList.get(position).isVisible) {
                     holder.expand.setVisibility(view.GONE);
                     toDoList.get(position).isVisible = false;
                 }
                 else {
                     holder.expand.setVisibility(view.VISIBLE);
                     toDoList.get(position).isVisible = true;


                     TransitionManager.beginDelayedTransition(holder.taskcard,new AutoTransition());
                 }
             }
         });

         holder.add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int finalvalue = Integer.parseInt(holder.finalStatus.getText().toString());
                 int initialValue = Integer.parseInt(holder.current.getText().toString());

                 if(initialValue<finalvalue) {

                     DB.updateStatus(String.valueOf(++initialValue), todo);
                     holder.progressBar.setProgress(initialValue);
                     holder.progressBar.setMax(finalvalue);
                     holder.current.setText(String.valueOf(initialValue));
                 }
                 else{
                     Toast.makeText(view.getContext(), "you complete the task", Toast.LENGTH_SHORT).show();
                 }
             }
         });

        holder.subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int finalvalue = Integer.parseInt(holder.finalStatus.getText().toString());
                int initialValue = Integer.parseInt(holder.current.getText().toString());

                if(initialValue == 0) {
                    Toast.makeText(view.getContext(), "please start the task", Toast.LENGTH_SHORT).show();
                }
                else{
                    DB.updateStatus(String.valueOf(--initialValue), todo);
                    holder.progressBar.setProgress(initialValue);
                    holder.progressBar.setMax(finalvalue);
                    holder.current.setText(String.valueOf(initialValue));
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToDo item = toDoList.get(position);
                DB.deleteTask(item.getId());
                toDoList.remove(position);
                MainActivity.setLayout();
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView task, current, finalStatus;
        public ImageView add,delete,subtract;
        public LinearLayout taskcard,expand;
        public ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            task = itemView.findViewById(R.id.task);
            current = itemView.findViewById(R.id.currentStatus);
            finalStatus = itemView.findViewById(R.id.total);
            add = itemView.findViewById(R.id.add);
            delete = itemView.findViewById(R.id.delete);
            subtract = itemView.findViewById(R.id.subtract);
            expand = itemView.findViewById(R.id.expand);
            taskcard = itemView.findViewById(R.id.taskcard);
            progressBar = itemView.findViewById(R.id.progressBar);

        }
    }
}
