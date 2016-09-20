package com.example.user.epicfitproject.activities;

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
import com.example.user.epicfitproject.model.user.UsersManager;

public class LogInActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private static final int REG_SUCCESS=14;
    private CheckBox rememberMeCheckBox;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        SharedPreferences preffs = getSharedPreferences("CurrentlyLogged", Context.MODE_PRIVATE);
        String loggedUserEmail = preffs.getString("loggedUser", "nope");
        if(!loggedUserEmail.equals("nope") && UsersManager.getInstance(this).existsUser(loggedUserEmail)){
            Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }

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
                startActivityForResult(goRegister,REG_SUCCESS);

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
                if(rememberMeCheckBox.isChecked()){
                    SharedPreferences preffs = getSharedPreferences("CurrentlyLogged", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preffs.edit();
                    editor.putString("loggedUser", emailH);
                    editor.commit();
                }
                Intent intent = new Intent(LogInActivity.this, MainMenuActivity.class);
                intent.putExtra("LoggedUser",emailH);
                startActivity(intent);
                finish();
                }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REG_SUCCESS){
            if(resultCode==REG_SUCCESS){
                email.setText(getIntent().getStringExtra("email"));
                password.setText(getIntent().getStringExtra("password"));
                setResult(16);
            }
        }
    }
}
