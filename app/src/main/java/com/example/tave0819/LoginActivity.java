package com.example.tave0819;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_register;
    private EditText et_pwd;
    private EditText et_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

       // else
        et_id = findViewById(R.id.et_Id);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin(); // define below the code
            }
        });

        /*

        // test
        Button btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        */

        // register click method
        btn_register = (Button)findViewById(R.id.btn_register);
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
    private void userLogin(){
        final String id  = et_id.getText().toString();
        final String pwd = et_pwd.getText().toString();

        JSONObject params = new JSONObject();
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
            return ;
        }
        if(TextUtils.isEmpty(pwd)){
            et_pwd.setError("패스워드를 입력해주세요.");
            et_pwd.requestFocus();
            return;
        }

        Log.v("params complete: ", "true");

        // condition satisfied
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLs.url_login, params, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if (!response.getBoolean("error")) {
                                Log.v("if loop: ", "loop succeed");
                            }
                            else {
                            Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("password", pwd);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(jsonRequest);
    }
}