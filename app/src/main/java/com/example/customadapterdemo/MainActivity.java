package com.example.customadapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    final String TAG = "DEBUG";
    UserListAdapter adapter;
    ListView listView;
    Button sortBySex, sortByName, sortByPhone;
    GsonBuilder gb = new GsonBuilder();
    Gson gson = gb.create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        sortByName = findViewById(R.id.namebtn);
        sortByPhone = findViewById(R.id.phonebtn);
        sortBySex = findViewById(R.id.sexbtn);



        final ArrayList<User> users = new ArrayList<>();

        sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(users);
                adapter.notifyDataSetChanged();
            }
        });
        sortByPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(users, new UserPhoneComparator());
                adapter.notifyDataSetChanged();
        }
        });
        sortBySex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(users, new UserSexComparator());
                adapter.notifyDataSetChanged();
            }
        });
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
