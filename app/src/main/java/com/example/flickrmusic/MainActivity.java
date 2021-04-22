package com.example.flickrmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.flickrmusic.PlayerActivity.musicService;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    public static ArrayList<MusicFiles> musicFiles;
    static boolean shuffleBoolean = false;
    static boolean repeatBoolean = false;
    static ArrayList<MusicFiles> albums = new ArrayList<>();
    Button play_pause, stop;
    RelativeLayout menu_layout;
    TextView userName;
    Users user;
    private TabLayout tabLayout;
    private androidx.viewpager.widget.ViewPager viewPager;

    public static ArrayList<MusicFiles> getAllAudio(Context context) {
        ArrayList<String> duplicate = new ArrayList<>();
        //Methods for fetching all the files from the local storage
        //and then store it in array list of model <MusicFiles>
        ArrayList<MusicFiles> tempAudioFiles = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media._ID

        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);
                String id = cursor.getString(5);
                MusicFiles musicFiles = new MusicFiles(path, title, artist, album, duration, id);
                Log.e("Path" + path, "Album : " + album);
                tempAudioFiles.add(musicFiles);
                if (!duplicate.contains(album)) ;
                {
                    albums.add(musicFiles);
                    duplicate.add(album);
                }
            }
            cursor.close();
        }
        return tempAudioFiles;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.permission();
        //setting Fragments
        setUpFragments();
        //Fetching Intent
        setUpUserProfile();
        intiOnClickListeners();
    }

    private void setUpFragments() {
        ViewPager adapter = new ViewPager(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        adapter.AddFragment(new ListFragment(), "Songs");
        adapter.AddFragment(new Album(), "Album");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void setUpUserProfile() {
        Intent intent = getIntent();
        user = (Users) intent.getSerializableExtra("USER_DETAILS");
        if (user != null)
            Log.e("USER name", user.getEmail());
        String full_name = user.getFirstName() + " " + user.getLastName();
        userName.setText(full_name);

    }

    private void intiOnClickListeners() {

        //method for initializing all the on click listener defined
        menu_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //creating Menu for main screen
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.logout:
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                if (musicService != null) {
                                    musicService.stop();
                                    musicService.release();
                                }
                                deleteSharedPreferences();
                                startActivity(intent);
                                finish();
                                break;
                            case R.id.profile:
                                Intent intent1 = new Intent(getApplicationContext(), MyProfile.class);
                                intent1.putExtra("USER_DETAIL", (Serializable) user);
                                startActivity(intent1);
                                break;
                            default:
                                return false;


                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.main_popup_menu);
                popupMenu.show();
            }
        });

    }

    private void deleteSharedPreferences() {

        //Deletes the saved user data from saved preferences
        SharedPreferences mpref;
        SharedPreferences.Editor editor;
        mpref = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        editor = mpref.edit();
        editor.clear();
        editor.commit();
    }

    public void initViews() {
        //Iniatializes the views used in the corresponding layout  the layout
        play_pause = findViewById(R.id.play_pause_btn);
        stop = findViewById(R.id.stop);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        menu_layout = findViewById(R.id.menu_layout);
        userName = findViewById(R.id.user_name);

    }

    private void permission() {
        //asks for the permissions required by the app

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //If permission is granted it will fetch all the music files from the local storage
            musicFiles = getAllAudio(this);
            //then initializes the views
            initViews();
        } else {
            //else it will ask the user for the required permissions
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Executed after requesting permissions
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                musicFiles = getAllAudio(this);
                initViews();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }


}