package com.example.flickrmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {
    TextView firstName, secondName, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Users users = (Users) getIntent().getExtras().getSerializable("USER_DETAIL");
        //Testing User data came through Intent
        if (users != null)
            Log.e("USER details", users.getFirstName());
        else
            Log.e("USER details", "NULL");
        firstName = findViewById(R.id.data_first_name);
        secondName = findViewById(R.id.data_second_name);
        email = findViewById(R.id.data_email);

        firstName.setText(users.getFirstName());
        secondName.setText(users.getLastName());
        email.setText(users.getEmail());

    }
}