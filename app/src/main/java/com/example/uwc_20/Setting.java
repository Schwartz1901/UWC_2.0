package com.example.uwc_20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Setting extends AppCompatActivity {

    TextInputLayout fullname,dob,gender,id,email;

    String NAME, DOB, GENDER, ID, EMAIL;

    DatabaseReference reference;
    DatabaseReference user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        SharedPreferences prefs= getSharedPreferences("USER_ID", MODE_PRIVATE);
        String userId = prefs.getString("userId", "");

        reference = FirebaseDatabase.getInstance("https://uwc-ver2-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user");

        fullname = findViewById(R.id.fullname);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);
        id = findViewById(R.id.id);

        showAlluserdata();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        // TODO: handle the post
                        UserHelper userProfile = postSnapshot.getValue(UserHelper.class);

                        if(userProfile != null){
                            if (userProfile.id.equals(userId)) {
                                user = postSnapshot.getRef();
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Setting.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showAlluserdata(){

        Intent intent = getIntent();
        NAME = intent.getStringExtra("fullname");
        DOB = intent.getStringExtra("doB");
        EMAIL = intent.getStringExtra("email");
        ID = intent.getStringExtra("id");
        GENDER = intent.getStringExtra("gender");

//        fullname.getEditText().setText(NAME);
//        dob.getEditText().setText(DOB);
//        studentid.getEditText().setText(STUDENTID);
//        email.getEditText().setText(EMAIL);
//        phonenumber.getEditText().setText(PHONENO);
//        id.getEditText().setText(ID);
//        password.getEditText().setText(PASSWORD);
    }

    public void update(View view){
        if(isNameChanged() || isDobchanged() || isGenderChanged() || isEmailChanged() || isIDChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Data is same and can not be updated", Toast.LENGTH_LONG).show();
    }

    private boolean isIDChanged() {
        if(!ID.equals(id.getEditText().getText().toString())){
            user.child("id").setValue(id.getEditText().getText().toString());
            ID = id.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isEmailChanged() {
        if(!EMAIL.equals(email.getEditText().getText().toString())){
            user.child("email").setValue(email.getEditText().getText().toString());
            EMAIL = email.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isGenderChanged() {
        if(!GENDER.equals(gender.getEditText().getText().toString())){
            user.child("gender").setValue(gender.getEditText().getText().toString());
            GENDER = gender.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isDobchanged() {
        if(!DOB.equals(dob.getEditText().getText().toString())){
            user.child("Dob").setValue(dob.getEditText().getText().toString());
            DOB = dob.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!NAME.equals(fullname.getEditText().getText().toString())){
            user.child("name").setValue(fullname.getEditText().getText().toString());
            NAME = fullname.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }
}