package com.example.user.epicfitproject.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.user.UsersManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirmPass;
    private EditText email;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        confirmPass = (EditText) findViewById(R.id.editText_confirm);
        email = (EditText) findViewById(R.id.editText_email);
        create = (Button) findViewById(R.id.button_createProfile);
        SharedPreferences sharedPreferences = getSharedPreferences("CurrentlyLogged", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("loggedUser").commit();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                String passWord2 = confirmPass.getText().toString();
                String eMail = email.getText().toString();

                if(userName.isEmpty()){
                    username.setError("Username is compulsory");
                    username.requestFocus();
                    return;
                }

                if(passWord.isEmpty()){
                    password.setError("Password is compulsory");
                    password.requestFocus();
                    return;
                }

                if(passWord2.isEmpty()){
                    confirmPass.setError("Password2 is compulsory");
                    confirmPass.requestFocus();
                    return;
                }

                if(eMail.isEmpty()){
                    email.setError("Email is compulsory");
                    email.requestFocus();
                    return;
                }

                if(!passWord.equals(passWord2)){
                    password.setError("Passwords missmatch");
                    password.setText(null);
                    confirmPass.setText(null);
                    password.requestFocus();
                    return;
                }
                if(!check(eMail)){
                    email.setError("Enter valid email");
                    email.setText(null);
                    email.requestFocus();
                    return;
                }
                if(UsersManager.getInstance(RegisterActivity.this).existsUser(eMail)){
                    username.setError("User already exists");
                    username.setText(null);
                    username.requestFocus();
                    Log.e("F", "Returns existing user");
                    return;
                }

                UsersManager.getInstance(RegisterActivity.this).regUser(RegisterActivity.this, userName, passWord, eMail);
                Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                setResult(14);
                Intent intent = new Intent(RegisterActivity.this,LogInActivity.class);
               // intent.putExtra("username", username.getText().toString());
                intent.putExtra("password", passWord);
                intent.putExtra("email",eMail);
                startActivity(intent);
                finish();
            }
        });
    }
    private static boolean check(String s ){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches()){
            return true;
        }

        return false;
    }


}
