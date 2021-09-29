package com.example.tave0915;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private RecyclerView recyclerView;
    ArrayList<WelfareInfoComponent> VIEW_COMPONENTS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // recycler view adapter
        RecyclerView recyclerView = findViewById(R.id.list_container) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        WelfareViewAdapter adapter = new WelfareViewAdapter(VIEW_COMPONENTS);
        recyclerView.setAdapter(adapter) ;

        // btn_back button listener
        ImageButton btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}