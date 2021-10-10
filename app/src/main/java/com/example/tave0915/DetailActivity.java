package com.example.tave0915;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();

        TextView tv_detail = (TextView) findViewById(R.id.tv_detail);
        TextView tv_who = (TextView) findViewById(R.id.tv_who);
        TextView tv_criteria = (TextView) findViewById(R.id.tv_criteria);

        try{
            tv_detail.setText(bundle.getString("detail",""));
            tv_who.setText(bundle.getString("who",""));
            tv_criteria.setText(bundle.getString("criteria",""));

            Log.v("DetailActivity bundle data to text view","start");
        }
        catch(Exception err){
            err.printStackTrace();
            Log.v("DetailActivity bundle data to text view","error");
        }

        // 뒤로가기 버튼에 대한 동작을 구현해야 함...

    }
}