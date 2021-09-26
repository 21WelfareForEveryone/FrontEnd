package com.example.tave0915;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private ChatRVAdapter chatRVAdapter;
    private ArrayList<ChatModel>chatModelArrayList;

    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    String token = ""; // token for chatbot request

    Boolean isSuccess = false;
    int statusCode;
    int message_type;
    String message_content;
    JSONArray welfare_info;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // keyboard disappeared
        EditText et_message = (EditText)findViewById(R.id.et_message);
        et_message.setShowSoftInputOnFocus(false);

        //RecyclerView Event Listener
        RecyclerView chatRVList = (RecyclerView)findViewById(R.id.chatList);
        chatModelArrayList = new ArrayList<>();
        chatRVAdapter =  new ChatRVAdapter(chatModelArrayList, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        chatRVList.setLayoutManager(manager);
        chatRVList.setAdapter(chatRVAdapter);

        // push button event listener
        Button btn_transfer = (Button)findViewById(R.id.btn_transfer);
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_message.getText().toString().isEmpty()){
                    Toast.makeText(ChatActivity.this, "메세지를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    getResponse(et_message.getText().toString());
                    et_message.setText("");
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.navigation_2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.navigation_1:
                        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    case R.id.navigation_2:
                        return true;
                    case R.id.navigation_3:
                        Intent intent3 = new Intent(ChatActivity.this, MapActivity.class);
                        startActivity(intent3);
                        finish();
                        return true;
                    case R.id.navigation_4:
                        Intent intent4 = new Intent(ChatActivity.this, MyProfileActivity.class);
                        startActivity(intent4);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    // get & post api
    private void getResponse(String text){
        chatModelArrayList.add(new ChatModel(text, USER_KEY));
        chatRVAdapter.notifyDataSetChanged();

        // params : post에 필요한 변수
        JSONObject params = new JSONObject();
        try{
            params.put("token", token);
            params.put("chat_message", text);
            Log.v("params complete: ", "true");
        }
        catch(JSONException e){
            e.printStackTrace();
            return;
        }

        String url_test = "http://34.64.177.178/chatbot/getresponse/dummy0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_test, params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.v("response: ", response.toString());
                try{
                    isSuccess = response.getBoolean("success");
                    statusCode = response.getInt("statusCode");
                    message_type = response.getInt("message_type");
                    message_content = response.getString("message_content");
                    welfare_info = response.getJSONArray("welfare_info");

                    Log.v("chatbot message response isSuccess: ", isSuccess.toString());
                    Log.v("chatbot message  response statusCode : ", Integer.toString(statusCode));
                    Log.v("chatbot message response message_type: ", Integer.toString(message_type));

                    if(message_type == 0){
                        chatModelArrayList.add(new ChatModel(message_content,BOT_KEY));
                        chatRVAdapter.notifyDataSetChanged();
                    }
                    else if(message_type == 1){
                        chatModelArrayList.add(new ChatModel("message type : 1",BOT_KEY));
                        chatRVAdapter.notifyDataSetChanged();
                    }
                    else{
                        chatModelArrayList.add(new ChatModel("알 수 없는 내용입니다.",BOT_KEY));
                        chatRVAdapter.notifyDataSetChanged();
                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                    Log.v("chatbot message response error", e.getMessage());
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                chatModelArrayList.add(new ChatModel("Server Error!", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
                isSuccess = false;
                statusCode = 500;
                Log.v("chatbot Server Error", "response denied");
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        Log.v("chatbot statusCode", Integer.toString(statusCode));

        if(isSuccess){
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
            return;
        }
        else{
            Log.v("can not add requestQueue, server error", "response error");
            return;
        }
    };
}