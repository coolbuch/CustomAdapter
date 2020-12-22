package com.example.customadapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    final String TAG = "DEBUG";
    UserListAdapter adapter;
    ListView listView;
    GsonBuilder gb = new GsonBuilder();
    Gson gson = gb.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        ArrayList<User> users = new ArrayList<>();
        try {
            InputStream is = getAssets().open("users.json");
            InputStreamReader reader = new InputStreamReader(is);
            Users us = gson.fromJson(reader, Users.class);
            Log.d(TAG, Arrays.toString(us.users.toArray()));
            users.addAll(us.users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        adapter = new UserListAdapter(this, users);

        listView.setAdapter(adapter);
    }
}
