package com.example.tave0915;

import static com.example.tave0915.URLs.url_welfare_recommend;
import static com.example.tave0915.URLs.url_welfare_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tave0915.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Category RV list
    private MainRVAdapter mainRVAdapter;
    private ArrayList<MainCategoryCard> categoryList;

    // Recommended Welfare Info RV list
    private WelfareViewAdapter welfareViewAdapter;
    private ArrayList<WelfareInfoComponent> welfareInfoComponentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("token");

        RecyclerView RV_category = (RecyclerView)findViewById(R.id.RV_category);
        categoryList = new ArrayList<>();

        for(int i = 0; i<16; i++){
            String categoryName = "카테고리 " + Integer.toString(i+1);
            MainCategoryCard categoryCard = new MainCategoryCard(categoryName);
            categoryList.add(categoryCard);
        }

        mainRVAdapter = new MainRVAdapter(categoryList, getApplicationContext(), token);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RV_category.setLayoutManager(linearLayoutManager);
        RV_category.setAdapter(mainRVAdapter);

        // Recommended welfare Info data loaded
        RecyclerView welfareRecommendedRV = (RecyclerView)findViewById(R.id.welfare_recommended_RV);
        welfareInfoComponentArrayList = new ArrayList<>();

        welfareViewAdapter = new WelfareViewAdapter(welfareInfoComponentArrayList);
        LinearLayoutManager welfareLinearLayoutManger = new LinearLayoutManager(this);

        welfareRecommendedRV.setLayoutManager(welfareLinearLayoutManger);
        welfareRecommendedRV.setAdapter(welfareViewAdapter);

        try{
            getRecommendWelfareInfo(token);
            Log.v("MainActivity recommended welfare info load process","pass");
        }
        catch(Exception err){
            Log.v("MainActivity recommended welfare info load process","failed");
        }

        // navigation onClickListener
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

    private void getRecommendWelfareInfo(String token){
        JSONObject params = new JSONObject();
        try{
            params.put("token", token);
            Log.v("MainActivity getRecommendWelfareInfo  params complete", "true");
        }
        catch(JSONException e){
            e.printStackTrace();
            return;
        }
        SharedPreferences recommendWelfareInfo = getSharedPreferences("recommendWelfareInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = recommendWelfareInfo.edit();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_welfare_recommend, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("MainActivity welfare  response", "success");
                try{
                    Boolean isSuccess = response.getBoolean("success");
                    int statusCode = response.getInt("statusCode");
                    String responseToken = response.getString("token");
                    JSONArray jar = response.getJSONArray("recommend_welfare_list");

                    Log.v("MainActivity response isSuccess", isSuccess.toString());
                    Log.v("MainActivity response statusCode", Integer.toString(statusCode));
                    Log.v("MainActivity number of welfare_list", Integer.toString(jar.length()));
                    Log.v("MainActivity jar", jar.toString());

                    editor.putBoolean("success", isSuccess);
                    editor.putInt("statusCode", statusCode);

                    if(jar.length() != 0){
                        int welfare_id = jar.getJSONObject(0).getInt("welfare_id");
                        String title = jar.getJSONObject(0).getString("title");
                        String summary = jar.getJSONObject(0).getString("summary");

                        editor.putInt("welfare_id", welfare_id);
                        editor.putString("title", title);
                        editor.putString("summary", summary);
                        editor.commit();
                    }
                    else{
                        editor.commit();
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("MainActivity welfare response", "failed");
                editor.putBoolean("success", false);
                editor.commit();
            }
        });

        Log.v("MainActivity jsonObjectRequest", jsonObjectRequest.toString());
        Log.v("MainActivity jsonObjectRequest url", jsonObjectRequest.getUrl());

        if(recommendWelfareInfo.getBoolean("success",false)){

            String text = "총 "+ Integer.toString(recommendWelfareInfo.getInt("totalNum",0)) + "개의 복지가 있습니다.";
            TextView tv_num_list = (TextView)findViewById(R.id.sub_title);
            tv_num_list.setText(text);

            welfareInfoComponentArrayList.add(new WelfareInfoComponent(
                    recommendWelfareInfo.getInt("welfare_id",0),
                    recommendWelfareInfo.getString("title", ""),
                    recommendWelfareInfo.getString("summary", ""),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0
            ));
            welfareViewAdapter.notifyDataSetChanged();
        }
        jsonObjectRequest.setShouldCache(false);
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}