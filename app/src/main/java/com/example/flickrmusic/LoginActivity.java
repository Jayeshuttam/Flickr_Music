package com.example.flickrmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public EditText email, password;
    Button login, signup, login_from_signup;
    ArrayList<Users> users;
    DBHelper dbHelper;
    SharedPreferences mpref;
    SharedPreferences.Editor editor;
    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initDatabase();
        initViews();
        initSharedPref();
        checkForSharedPref();
        initOnClickListeners();
        dbHelper.closeDataBase();
    }

    private void initOnClickListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                if (user_email.isEmpty() || !user_email.matches(emailPattern)) {
                    Toast.makeText(LoginActivity.this, "Please enter valid email", Toast.LENGTH_LONG).show();
                    email.setError("Invalid email ");
                } else if (user_password.isEmpty() || user_password.length() < 8) {
                    Toast.makeText(LoginActivity.this, "Please enter valid password", Toast.LENGTH_LONG).show();
                    password.setError("Invalid Email");
                } else {
                    checkForUser(user_email, user_password);

                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDatabase() {
        dbHelper = new DBHelper(getApplicationContext());
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.openDatabase();
        users = dbHelper.getAppCategoryDetail();
    }

    private void checkForSharedPref() {
        String email, password;
        email = mpref.getString("EMAIL", "");
        password = mpref.getString("PASSWORD", "");
        if (!email.isEmpty() && !password.isEmpty()) {
            checkForUser(email, password);

        }

    }

    private void checkForUser(String email, String password) {
        int flag = 0;
        for (int i = 0; i < users.size(); i++) {
            if (email.equals(users.get(i).getEmail()) && password.equals(users.get(i).getPasssword())) {
                Log.e("User entered email", email);
                Log.e("User entered password", password);
                Log.e("Email " + i, users.get(i).getEmail());
                Log.e("Password " + i, users.get(i).getPasssword());
                if (rememberMe.isChecked()) {
                    editor.putString("EMAIL", email);
                    editor.putString("PASSWORD", password);
                    editor.commit();
                }
                Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("USER_DETAILS", (Serializable) users.get(i));
                startActivity(intent);
                finish();
                flag = 1;
                break;
            }

        }
        if (flag == 0) {
            Toast.makeText(getApplicationContext(), "Login Failed !! User not found", Toast.LENGTH_LONG).show();
        }

    }

    private void initSharedPref() {
        mpref = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        editor = mpref.edit();
    }

    private void initViews() {
        login = findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_btn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rememberMe = findViewById(R.id.remeber_me);
    }


}