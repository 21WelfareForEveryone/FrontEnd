package com.example.tave0915;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.tave0915.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Category RV list
    private MainRVAdapter mainRVAdapter;
    private ArrayList<MainCategoryCard> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

//        RecyclerView RV_category = (RecyclerView)findViewById(R.id.RV_category);
//        categoryList = new ArrayList<>();
//        mainRVAdapter = new MainRVAdapter(categoryList, getApplicationContext());
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        RV_category.setLayoutManager(linearLayoutManager);
//        RV_category.setAdapter(mainRVAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.navigation_1:
                        return true;
                    case R.id.navigation_2:
                        Intent intent2 = new Intent(MainActivity.this, ChatActivity.class);
                        intent2.putExtras(bundle);
                        startActivity(intent2);
                        finish();
                        return true;
                    case R.id.navigation_3:
                        Intent intent3 = new Intent(MainActivity.this, MapActivity.class);
                        intent3.putExtras(bundle);
                        startActivity(intent3);
                        finish();
                        return true;
                    case R.id.navigation_4:
                        Intent intent4 = new Intent(MainActivity.this, MyProfileActivity.class);
                        intent4.putExtras(bundle);
                        startActivity(intent4);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}