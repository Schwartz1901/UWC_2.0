package com.example.uwc_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Task extends AppCompatActivity {
    private DatabaseReference reference;


    Calendar cal = Calendar.getInstance();

    int currentDay = cal.get(Calendar.DAY_OF_MONTH); // current day in the month
    int currentMonth = cal.get(Calendar.MONTH); // month...

    String[] months = {
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasklist);

        //String userId = getIntent().getStringExtra("USER_ID");
        SharedPreferences prefs= getSharedPreferences("USER_ID", MODE_PRIVATE);
        String userId = prefs.getString("userId", "");
        Log.d("User ID", userId);
        TextView current = (TextView) findViewById(R.id.currentDate);
        current.setText(currentDay + " " + months[currentMonth]);

        int[] taskLayout = new int[] {R.id.currentTask, R.id.nextTask1, R.id.nextTask2, R.id.nextTask3};
        reference = FirebaseDatabase.getInstance("https://uwc-ver2-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Tasks");

        int[] taskName = new int[] {R.id.currentTaskName, R.id.nextTaskName1, R.id.nextTaskName2, R.id.nextTaskName3};
        int[] taskMCP = new int[] {R.id.currentTaskMCP, R.id.nextTaskMCP1, R.id.nextTaskMCP2, R.id.nextTaskMCP3};
        int[] taskType = new int[] {R.id.currentTaskJob, R.id.nextTaskJob1, R.id.nextTaskJob2, R.id.nextTaskJob3};
        String[] taskId = new String[] {"", "", "", "", ""};

        //Query myTopPostsQuery = reference.child("userId").equalTo(userId).limitToFirst(4);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                if (snapshot.exists())
                {
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        // TODO: handle the post
                        TaskHelper taskProfile = postSnapshot.getValue(TaskHelper.class);
                        Log.d("Task5", "Ok");
                        if(taskProfile != null){
                            if (taskProfile.userId.equals(userId))
                            {
                                String id = taskProfile.id;
                                taskId[count] = taskProfile.id;
                                String mcp = taskProfile.mcp;
                                String type = taskProfile.type;

                                final TextView NameTextview = (TextView)findViewById(taskName[count]);
                                final TextView MCPTextview = (TextView)findViewById(taskMCP[count]);
                                final TextView TypeTextview = (TextView)findViewById(taskType[count]);

                                NameTextview.setText(id);
                                MCPTextview.setText(mcp);
                                TypeTextview.setText(type);

                                count++;
                                if (count>3) break;
                            }
                            else continue;
                        }
                        Log.d("Task6", "Ok");
                    }
                    for (int i = count;i<4;i++)
                    {
                        RelativeLayout layout = (RelativeLayout)findViewById(taskLayout[i]);
                        if (layout.getVisibility() == View.VISIBLE) {
                            layout.setVisibility(View.GONE);
                        }
                        else if (layout.getVisibility() == View.GONE) {
                            layout.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Task.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        for (int i = 0;i<4;i++)
        {
            RelativeLayout layout = (RelativeLayout)findViewById(taskLayout[i]);
            final int pos = i;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentCurrent=new Intent(Task.this, showTask.class);
                    intentCurrent.putExtra("TASK_ID", taskId[pos]);
                    startActivity(intentCurrent);
                }
            });
        }
    }


}
