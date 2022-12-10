package com.example.uwc_20;

import android.view.View;
//
//import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signin extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextName, editTextid;
    private Button signin;
    private FirebaseAuth mAuth;
    public String namee, idd;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.signin);

        signin = (Button)findViewById(R.id.btn_signin);
        signin.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.name);
        editTextid = (EditText) findViewById(R.id.id);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            startActivity(new Intent(Signin.this, Home.class));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_signin){
            userLogin();
        }
    }
    private void userLogin(){
        namee = editTextName.getText().toString().trim();
        idd = editTextid.getText().toString().trim();

        if(namee.isEmpty()){
            editTextName.setError("Name is required!");
            editTextName.requestFocus();
            return;
        }
        if(idd.length()<6){
            editTextid.setError("Min id is 6 characters");
            editTextid.requestFocus();
            return;
        }
        verifyUser();
    }

    private void verifyUser(){
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance("https://uwc-ver2-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user");
        Log.d("Data Snapshot", "Chuan bi snap shot");
        reference.child("user1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Data Snapshot1", "Chuan bi snap shot1");
                UserHelper userProfile = snapshot.getValue(UserHelper.class);

                if(userProfile != null){
                    String name = userProfile.name;
                    String Dob = userProfile.Dob;
                    String id = userProfile.id;
                    String email = userProfile.email;
                    String gender = userProfile.gender;

                    Log.d("Data Snapshot1", "Equal");
                    if((namee.equals(name)) && (idd.equals(id))){
                        Intent int1 = new Intent(Signin.this, Home.class);
                        int1.putExtra("USER_ID", id);
                        SharedPreferences prefs= getSharedPreferences("USER_ID", MODE_PRIVATE);
                        prefs.edit().putString("userId", id).apply();

                        startActivity(int1);
                    }
                    else{
                        Toast.makeText(Signin.this, "Name or id is wrong!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Signin.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
