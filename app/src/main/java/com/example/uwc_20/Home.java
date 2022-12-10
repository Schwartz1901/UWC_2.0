package com.example.uwc_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity{
    private Button logout;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        String userId = getIntent().getStringExtra("USER_ID");

        ImageButton calendar = (ImageButton) findViewById(R.id.btn_calendar);
        ImageButton task = (ImageButton) findViewById(R.id.btn_task);
        ImageButton chatting = (ImageButton) findViewById(R.id.btn_chatting);
        ImageButton map = (ImageButton) findViewById(R.id.btn_map);
        ImageButton setting = (ImageButton) findViewById(R.id.btn_setting);
        ImageButton information = (ImageButton) findViewById(R.id.btn_information);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Home.this, CalendarLayout.class);
                int1.putExtra("USER_ID", userId);
                startActivity(int1);
            }
        });
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Home.this, Task.class);
                int2.putExtra("USER_ID", userId);
                startActivity(int2);
            }
        });
        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(Home.this, Chatting.class);
                startActivity(int3);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(Home.this, Setting.class);
                int5.putExtra("USER_ID", userId);
                startActivity(int5);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int6 = new Intent(Home.this, Information.class);
                int6.putExtra("USER_ID", userId);
                startActivity(int6);
            }
        });

        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, MainActivity.class));
            }
        });
    }

    private void openMap() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:10.772983, 106.657860"));
        startActivity(intent);
    }
}
