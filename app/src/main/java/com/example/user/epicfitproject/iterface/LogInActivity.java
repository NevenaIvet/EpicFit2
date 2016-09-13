package com.example.user.epicfitproject.iterface;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.user.epicfitproject.R;
import com.example.user.epicfitproject.model.UsersManager;

public class LogInActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private CheckBox rememberMeCheckBox;
    private Button login;
//prommeni da se vliza s email i v xmla sloji da e email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = (EditText) findViewById(R.id.username_login);
        password = (EditText) findViewById(R.id.password_login);
        register = (Button) findViewById(R.id.button_register);
        login = (Button) findViewById(R.id.button_login);
        rememberMeCheckBox= (CheckBox) findViewById(R.id.checkBox);
        rememberMeCheckBox.setText("Remember me");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegister = new Intent(LogInActivity.this, RegisterActivity.class);
                email.setError(null);
                startActivity(goRegister);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailH = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(emailH.isEmpty()){
                    email.setError("Username is compulsory");
                    email.requestFocus();
                    return;
                }

                if(pass.isEmpty()){
                    password.setError("Password is compulsory");
                    password.requestFocus();
                    return;
                }

                if(!UsersManager.getInstance(LogInActivity.this).validateLogin(emailH, pass)){
                    email.setError("Invalid credentials");
                    email.setText(null);
                    password.setText(null);
                    email.requestFocus();
                    return;
                }
                //sledvashtoto trqbva da se opravi
                if(rememberMeCheckBox.isChecked()){
                    SharedPreferences preffs = getSharedPreferences("CurrentlyLogged", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preffs.edit();
                    editor.putString("loggedUser", emailH);
                    editor.commit();
                    Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                    intent.putExtra("LoggedUser",emailH);
                    startActivity(intent);
                    finish();
                }else {
                    //ako ne e cheknato bi trqbvalo da mi sloji username v athlete activity
                    //s usersmanagera i json trqbva da vzema username
                    Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                    intent.putExtra("email", emailH);

                    startActivity(intent);
                }
            }
        });

    }
}
