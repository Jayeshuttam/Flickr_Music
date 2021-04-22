package com.example.flickrmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText firstName, secondName, email, password;
    Button signup, login;
    InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViews();
        initDatabase();
        initOnClickListeners();


    }

    private void initViews() {
        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.submit_btn);
        login = findViewById(R.id.login_from_signup_btn);
    }

    private void initOnClickListeners() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                String user_firstName = firstName.getText().toString();
                String user_secondName = secondName.getText().toString();
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                if (validateInputs(user_firstName, user_secondName, user_email, user_password)) {
                    dbHelper.insertUser(user_firstName, user_secondName, user_email, user_password);
                    Toast.makeText(getApplicationContext(), "SignedUp successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean validateInputs(String user_firstName, String user_secondName, String user_email, String user_password) {
        boolean validate = true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String name = "[a-zA-Z]+";
        Log.e("firstName", user_firstName);
        Log.e("secondNamwe", user_secondName);
        if (user_firstName.isEmpty() || !user_firstName.matches(name)) {
            validate = false;
            firstName.setError("Invalid first name");
        }
        if (user_secondName.isEmpty() || !user_secondName.matches(name)) {
            validate = false;
            secondName.setError("Invalid first name");
        }
        if (user_email.isEmpty() || !user_email.matches(emailPattern)) {
            validate = false;
            email.setError("Invalid email");
        }
        if (user_password.isEmpty() || user_password.length() < 8) {
            validate = false;
            password.setError("Invalid Password");
        }
        return validate;
    }


    private void initDatabase() {
        dbHelper = new DBHelper(getApplicationContext());
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.openDatabase();
    }
}