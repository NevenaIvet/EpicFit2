package com.example.user.epicfitproject.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by user on 3.9.2016 г..
 */
public class UsersManager {
    private static UsersManager ourInstance;
    HashMap<String, User> registeredUsers;

    public static UsersManager getInstance(Activity activity) {
        if(ourInstance == null){
            ourInstance = new UsersManager(activity);
        }
        return ourInstance;
    }

    private UsersManager(Activity activity) {
        registeredUsers = new HashMap<>();
        String json = activity.getSharedPreferences("registeredUsers", Context.MODE_PRIVATE).getString("registeredUsers", "no users");
        Log.e("Loaded users", json);


        try {
            JSONArray jsonArr = new JSONArray(json);
            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject o = jsonArr.getJSONObject(i);

                User user = new User(o.getString("username"),
                        o.getString("password"),
                        o.getString("email"));

                registeredUsers.put(user.getUsername(), user);
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }


    public boolean existsUser(String userName) {
        return registeredUsers.containsKey(userName);
    }

    public void regUser(Activity activity, String userName, String passWord, String eMail) {
        User user = new User(userName, passWord, eMail);
        registeredUsers.put(userName, user);
        SharedPreferences prefs = activity.getSharedPreferences("registeredUsers", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String key = "registeredUsers";
        String value = "....";
        JSONArray jsonUsers = new JSONArray();
        try {

            for (User u : registeredUsers.values()) {
                JSONObject obj = new JSONObject();
                obj.put("username", u.getUsername());
                obj.put("password", u.getPassword());
                obj.put("email", u.getEmail());
                jsonUsers.put(obj);
            }


        }
        catch(JSONException e){

            Log.e("JSON", e.getMessage());
        }
        value = jsonUsers.toString();
        Log.e("JSON", value);
        editor.putString(key, value);
        editor.commit();
    }

    public boolean validateLogin(String userN, String pass) {

        if(!registeredUsers.containsKey(userN)){
            return false;
        }

        if(!registeredUsers.get(userN).getPassword().equals(pass)){
            return  false;
        }

        return true;
    }
}
