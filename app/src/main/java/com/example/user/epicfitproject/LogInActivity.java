package com.example.user.epicfitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.epicfitproject.goal.GoalActivity;
import com.example.user.epicfitproject.model.UsersManager;

public class LogInActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button register;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        register = (Button) findViewById(R.id.button_register);
        login = (Button) findViewById(R.id.button_login);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegister = new Intent(LogInActivity.this, RegisterActivity.class);
                username.setError(null);
                startActivity(goRegister);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userN = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(userN.isEmpty()){
                    username.setError("Username is compulsory");
                    username.requestFocus();
                    return;
                }

                if(pass.isEmpty()){
                    password.setError("Password is compulsory");
                    password.requestFocus();
                    return;
                }

                if(!UsersManager.getInstance(LogInActivity.this).validateLogin(userN, pass)){
                    username.setError("Invalid credentials");
                    username.setText(null);
                    password.setText(null);
                    username.requestFocus();
                    return;
                }


                Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                intent.putExtra("LoggedUser", userN);
                startActivity(intent);

            }
        });

    }
}
