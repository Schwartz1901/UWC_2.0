package com.example.uwc_20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity{
    private FirebaseUser user;
    private  DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        SharedPreferences prefs= getSharedPreferences("USER_ID", MODE_PRIVATE);
        String userId = prefs.getString("userId", "");

//        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://uwc-ver2-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user");
//        userID = user.getUid();

        final TextView nameTextview = (TextView)findViewById(R.id.name);
        final TextView DobTextview = (TextView)findViewById(R.id.dob);
        final TextView idTextview = (TextView)findViewById(R.id.id);
        final TextView emailTextview = (TextView)findViewById(R.id.email);
        final TextView genderTextview = (TextView)findViewById(R.id.gender);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        // TODO: handle the post
                        UserHelper userProfile = postSnapshot.getValue(UserHelper.class);

                        if(userProfile != null){
                            if (userProfile.id.equals(userId)) {
                                String name = userProfile.name;
                                String Dob = userProfile.Dob;
                                String id = userProfile.id;
                                String email = userProfile.email;
                                String gender = userProfile.gender;

                                nameTextview.setText(name);
                                DobTextview.setText(Dob);
                                idTextview.setText(id);
                                emailTextview.setText(email);
                                genderTextview.setText(gender);

                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Information.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
