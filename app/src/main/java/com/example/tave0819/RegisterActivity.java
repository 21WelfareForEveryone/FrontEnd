package com.example.tave0819;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends FragmentActivity {

    EditText et_username;
    EditText et_id;
    EditText et_pwd;

    Spinner spinner_income;
    Spinner spinner_address_city;
    Spinner spinner_address_gu;

    String[] income_items = {"1분위", "2분위", "3분위", "4분위", "5분위", "6분위", "7분위", "8분위","9분위"};
    int Income;

    String[] city_items = {"서울특별시"};
    String[] local_items = {"강북구","강서구", "강동구", "강남구", "마포구", "영등포구", "관악구", "종로구", "노원구"};
    String City;
    String Local;

    // boolean type variables
    RadioGroup RG_gender;
    RadioButton rb_male;
    RadioButton rb_female;

    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hide actionbar
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        // if already loggedin then pass the register process
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            return;
        }

        // username, email, pwd
        et_username = (EditText)findViewById(R.id.et_username);
        et_id = (EditText)findViewById(R.id.et_id);
        et_pwd = (EditText)findViewById(R.id.et_pwd);

        // gender
        rb_male =  (RadioButton)findViewById(R.id.btn_male);
        rb_female = (RadioButton)findViewById(R.id.btn_female);
        RG_gender = (RadioGroup)findViewById(R.id.genderRadioGroup);

        // spinner(소득분위)
        spinner_income = (Spinner)findViewById(R.id.spinner_income);

        // spinner income list production
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, income_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_income.setAdapter(adapter);
        spinner_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Income = income_items[position];
                Income = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Income =  income_items[0];
                Income = -1;
            }
        });

        // spinner(주소)
        spinner_address_city = (Spinner)findViewById(R.id.spinner_address_city);
        spinner_address_gu = (Spinner)findViewById(R.id.spinner_address_gu);

        ArrayAdapter<String> adapter_address_city = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city_items);
        adapter_address_city.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_address_city.setAdapter(adapter_address_city);
        spinner_address_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City = city_items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                City = null;
            }
        });

        ArrayAdapter<String> adapter_address_gu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, local_items);
        adapter_address_gu.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_address_gu.setAdapter(adapter_address_gu);
        spinner_address_gu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Local = local_items[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Local = null;
            }
        });

        // 추가 예정.

        // register button click event
        // register button
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                Toast.makeText(getApplicationContext(), "Register Process Start", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registerUser(){
        final String user_name = et_username.getText().toString().trim();
        final String user_id = et_id.getText().toString().trim();
        final String user_password = et_pwd.getText().toString().trim();

        Boolean gender = null;

        // gender
        if(RG_gender.getCheckedRadioButtonId() == R.id.btn_male){
            gender = true;
        }
        else if(RG_gender.getCheckedRadioButtonId() == R.id.btn_female) {
            gender = false;
        }
        final Boolean user_gender = gender;
        // income
        final int user_income = Income;
        // address
        final String user_address = City + " " + Local;


        // log list for variable request check
        Log.v("user_name_check", "user_name: " + user_name);
        Log.v("user_id_check","user_id: " + user_id);
        Log.v("user_password_check","user_password: " + user_password);
        Log.v("user_gender_check", "user_gender: " + user_gender);
        Log.v("user_income_check", "user_income: " + user_income);
        Log.v("user_address_check", "user_address: " + user_address);

        // check if variables are not selected
        if(TextUtils.isEmpty(user_name)){
            et_username.setError("이름을 입력하시기 바립니다.");
            et_username.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(user_id)){
            et_id.setError("아이디를 입력하시기 바립니다.");
            et_id.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(user_password)){
            et_pwd.setError("패스워드를 입력하시기 바립니다.");
            et_pwd.requestFocus();
            return;
        }
        if(user_gender == null){
            rb_female.setError("성별을 입력해주시기 바립니다.");
            rb_female.requestFocus();
            return;
        }

        if(user_income == -1){
            spinner_income.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(user_address)){
            spinner_address_city.requestFocus();
            return ;
        }

        // Register Request
        JSONObject params = new JSONObject();

        try{
            params.put("user_id", user_id);
            params.put("user_password", user_password);
            params.put("user_name", user_name);
            params.put("user_gender", user_gender);
            params.put("user_income", user_income);
            params.put("user_address",user_address);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLs.url_register, params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{

                            if(!response.getBoolean("error")){
                                Toast.makeText(getApplicationContext(),response.getString("message"), Toast.LENGTH_SHORT).show();

                                // getting the user from the response
                                JSONObject userJson = response.getJSONObject("user");
                                //User user = new User();
                                //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                Toast.makeText(getApplicationContext(), "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
        };
        VolleySingleton.getInstance(this).addToRequestQueue(jsonRequest);
    }
}