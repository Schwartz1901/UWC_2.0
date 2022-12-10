package com.example.uwc_20;

import android.view.View;
public class TaskHelper {
    String id, check, type, mcp, description, route, userId;
    public TaskHelper() {
    }
    public TaskHelper(String check, String description, String id, String mcp, String route, String type, String userId) {
        this.id = id;
        this.check = check;
        this.type = type;
        this.mcp = mcp;
        this.description = description;
        this.route = route;
        this.userId = userId;
    }
    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMCP() {
        return mcp;
    }

    public void setMCP(String mcp) {
        this.mcp = mcp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId= userId;
    }


    public void update(View view){

    }
}
