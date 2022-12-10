package com.example.uwc_20;

import android.view.View;

public class UserHelper {
    String name, Dob, email, id, gender;

    public UserHelper() {
    }
    public UserHelper(String Dob, String gender,String email, String id, String name) {
        this.name = name;
        this.Dob = Dob;
        this.email = email;
        this.gender = gender;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String Dob) {
        this.Dob = Dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void update(View view){

    }
}
