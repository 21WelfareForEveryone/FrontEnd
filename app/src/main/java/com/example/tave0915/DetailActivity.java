package com.example.tave0915;

import static com.example.tave0915.URLs.url_welfare_read;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        int welfare_id = bundle.getInt("welfare_id",0);

        try{
            getDetailInfo(welfare_id);
            Log.v("DetailActivity bundle data to text view","start");
        }
        catch(Exception err){
            err.printStackTrace();
            Log.v("DetailActivity bundle data to text view","error");
        }

        // 신청하기 버튼 동작 구현
        Button btn_sign = (Button)findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // web-view
            }
        });
    }

    private void getDetailInfo(int welfare_id){
        JSONObject params = new JSONObject();
        try{
            params.put("welfare_id", welfare_id);
            Log.v("DetailActivity getDetailInfo  params complete", "true");
        }
        catch(JSONException e){
            e.printStackTrace();
            return;
        }

        SharedPreferences detailInfo = getSharedPreferences("detailInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = detailInfo.edit();

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_welfare_read, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("DetailActivity welfare response", "success");
                try{
                    Boolean isSuccess = response.getBoolean("success");
                    int statusCode = response.getInt("statusCode");
                    int welfare_id = response.getInt("welfare_id");
                    String title = response.getString("title");
                    String summary = response.getString("summary");
                    String who = response.getString("who");
                    String criteria = response.getString("criteria");
                    String how = response.getString("how");
                    String calls = response.getString("calls");
                    String sites = response.getString("sites");
                    int like_count = response.getInt("like_count");

                    editor.putBoolean("success", isSuccess);
                    editor.putInt("statusCode", statusCode);
                    editor.putInt("welfare_id", welfare_id);
                    editor.putString("title", title);
                    editor.putString("summary", summary);
                    editor.putString("who", who);
                    editor.putString("criteria", criteria);
                    editor.putString("how", how);
                    editor.putString("calls", calls);
                    editor.putString("sites", sites);
                    editor.putInt("like_count", like_count);
                    editor.commit();
                }
                catch(JSONException err){
                    err.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.v("DetailActivity welfare response", "failed");
                editor.putBoolean("success", false);
                editor.commit();
            }
        });

        Log.v("DetailActivity jsonObjectRequest", jsonObjectRequest.toString());
        Log.v("DetailActivity jsonObjectRequest url", jsonObjectRequest.getUrl());

        if(detailInfo.getBoolean("success", false)){
            TextView tv_detail = (TextView) findViewById(R.id.tv_detail);
            TextView tv_who = (TextView) findViewById(R.id.tv_who);
            TextView tv_criteria = (TextView) findViewById(R.id.tv_criteria);
            TextView tv_how =  (TextView) findViewById(R.id.tv_how);
            TextView tv_calls =  (TextView) findViewById(R.id.tv_calls);
            TextView tv_sites =  (TextView) findViewById(R.id.tv_sites);

            tv_detail.setText(detailInfo.getString("detail",""));
            tv_who.setText(detailInfo.getString("who",""));
            tv_criteria.setText(detailInfo.getString("criteria",""));
            tv_how.setText(detailInfo.getString("how",""));
            tv_calls.setText(detailInfo.getString("calls",""));
            tv_sites.setText(detailInfo.getString("sites",""));

            ImageView welfare_info_img = (ImageView)findViewById(R.id.welfare_info_img);
            // category 별로 이미지 사진 첨부
            // post 요청시 category 값을 받아온다면 바로 호출 가능..
            int category_num = 1;
            switch(category_num){
                case 0:
                    welfare_info_img.setImageResource(R.drawable.img_category_00);
                case 1:
                    welfare_info_img.setImageResource(R.drawable.img_category_01);
                case 2:
                    welfare_info_img.setImageResource(R.drawable.img_category_02);
                case 3:
                    welfare_info_img.setImageResource(R.drawable.img_category_03);
                case 4:
                    welfare_info_img.setImageResource(R.drawable.img_category_04);
                case 5:
                    welfare_info_img.setImageResource(R.drawable.img_category_05);
                case 6:
                    welfare_info_img.setImageResource(R.drawable.img_category_06);
                case 7:
                    welfare_info_img.setImageResource(R.drawable.img_category_07);
                case 8:
                    welfare_info_img.setImageResource(R.drawable.img_category_08);
                case 9:
                    welfare_info_img.setImageResource(R.drawable.img_category_09);
                case 10:
                    welfare_info_img.setImageResource(R.drawable.img_category_10);
                case 11:
                    welfare_info_img.setImageResource(R.drawable.img_category_11);
                case 12:
                    welfare_info_img.setImageResource(R.drawable.img_category_12);
                case 13:
                    welfare_info_img.setImageResource(R.drawable.img_category_13);
                case 14:
                    welfare_info_img.setImageResource(R.drawable.img_category_14);
                case 15:
                    welfare_info_img.setImageResource(R.drawable.img_category_15);
                default:
                    welfare_info_img.setImageResource(R.drawable.img_category_00);
            }

        }
        jsonObjectRequest.setShouldCache(false);
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}