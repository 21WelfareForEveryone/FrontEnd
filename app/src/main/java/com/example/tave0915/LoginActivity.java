package com.example.tave0915;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tave0915.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    
    private EditText et_pwd;
    private EditText et_id;

    public Boolean isSuccess;
    public int  statusCode;
    public String mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        et_id = findViewById(R.id.et_Id);
        et_pwd = findViewById(R.id.et_pwd);
        Button btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                UserLoginInfo loginInfoOnClick = (UserLoginInfo) userLogin();
//                Boolean loginSuccess = loginInfoOnClick.getIsSuccess();
//                String mToken = loginInfoOnClick.getToken();
//                Log.v("userLoginInfo loginSuccess", loginSuccess.toString());
//                Log.v("userLoginInfo mToken", mToken);

                userLogin();
                if(isSuccess){
                    Log.v("Login success?", isSuccess.toString());
                    // 해당 값들을 저장할 수 있는 함수 구현(json형태로 다른 파트로 이동)
                    Bundle bundle = new Bundle();
                    bundle.putString("mToken", mToken);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    if(bundle!=null){
                        intent.putExtras(bundle);
                    }
                    Log.v("intent change", "start!");
                    startActivity(intent);
                    finish();
                }
                else{
                    Log.v("Login success?","failed");
                }
            }
        });

        // register click method
        Button btn_register = (Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // userLogin()
    public void userLogin(){
        final String id  = et_id.getText().toString();
        final String pwd = et_pwd.getText().toString();

        JSONObject params = new JSONObject();
//        UserLoginInfo userLoginInfo = new UserLoginInfo();
//        userLoginInfo.setToken("test");
//        userLoginInfo.setIsSuccess(true);
//        userLoginInfo.setStatusCode(777);

        try{
            params.put("user_id", id);
            params.put("user_password", pwd);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        // blank -> request info
        if(TextUtils.isEmpty(id)){
            et_id.setError("아이디를 입력해주세요.");
            et_id.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            et_pwd.setError("패스워드를 입력해주세요.");
            et_pwd.requestFocus();
            return;
        }

        Log.v("params complete: ", "true");

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLs.url_login, params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.v("response: ", response.toString());
                try{
//                    userLoginInfo.setIsSuccess(response.getBoolean("success"));
//                    userLoginInfo.setToken(response.getString("token"));
//                    userLoginInfo.setStatusCode(response.getInt("statusCode"));

                    isSuccess = response.getBoolean("success");
                    mToken = response.getString("token");
                    statusCode =  response.getInt("statusCode");

                    Log.v("on login response isSuccess: ", isSuccess.toString());
                    Log.v("on login response statusCode : ", Integer.toString(statusCode));
                    Log.v("on login response mToken: ", mToken);
                }
                catch(JSONException e){
                    e.printStackTrace();
                    Log.v("에러 ", e.getMessage());

                    isSuccess = false;
                    mToken = "";
                    statusCode = 500;
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        isSuccess = false;
                        mToken = "";
                        statusCode = 500;
                        Log.v("Server Error", "response denied");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", id);
                params.put("user_password", pwd);
                return params;
            }
        };
//        userLoginInfo.setIsSuccess(isSuccess);
//        userLoginInfo.setToken(mToken);
//        userLoginInfo.setStatusCode(statusCode);

        Log.v("jsonRequest", jsonRequest.toString());
        Log.v("jsonRequest url: ", jsonRequest.getUrl());

        VolleySingleton.getInstance(this).addToRequestQueue(jsonRequest);
        if(isSuccess){
            Toast.makeText(getApplicationContext(), "로그인에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}