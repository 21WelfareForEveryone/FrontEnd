package com.example.tave0819;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "LoginTest";
    private static final String KEY_USERNAME = "user_name";
    private static final String KEY_ID = "user_id";
    private static final String KEY_PWD = "user_password";
    private static final String KEY_GENDER = "user_gender";
    private static final String KEY_INCOME = "user_income";
    private static final String KEY_ISMULTICULTURAL = "user_is_multiCultural";
    private static final String KEY_MTOKEN = "user_mToken";
    private static final String KEY_AGE = "user_age";
    private static final String KEY_ADDRESS = "user_address";
    private static final String KEY_FAMILYSTATE = "user_family_state";
    private static final String KEY_LIFECYCLE = "user_life_cycle";
    private static final String KEY_ISDISABLED = "user_is_disabled";
    private static final String KEY_ISVETERANS = "user_is_veterans";

    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_ID, user.getId());
        editor.putString(KEY_PWD, user.getPwd());
        editor.putBoolean(KEY_GENDER, user.getGender());
        editor.putInt(KEY_INCOME, user.getIncome());
        editor.putBoolean(KEY_ISMULTICULTURAL, user.getIsMultiCultural());
        editor.putBoolean(KEY_ISVETERANS, user.getIsVeterans());
        editor.putBoolean(KEY_FAMILYSTATE, user.getFamilyState());
        editor.putInt(KEY_LIFECYCLE, user.getLifeCycle());
        editor.putBoolean(KEY_ISDISABLED, user.getIsDisabled());
        editor.putString(KEY_MTOKEN, user.getmToken());
        editor.putInt(KEY_AGE, user.getAge());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String pushToken = extractFirebaseToken();
        return new User(
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_PWD, null),
                sharedPreferences.getString(KEY_ADDRESS, null),
                sharedPreferences.getString(KEY_MTOKEN, null),
                sharedPreferences.getInt(KEY_AGE, 0),
                sharedPreferences.getInt(KEY_LIFECYCLE, 0),
                sharedPreferences.getInt(KEY_INCOME, 0),
                sharedPreferences.getBoolean(KEY_ISVETERANS, false),
                sharedPreferences.getBoolean(KEY_GENDER, false),
                sharedPreferences.getBoolean(KEY_ISMULTICULTURAL, false),
                sharedPreferences.getBoolean(KEY_FAMILYSTATE, false),
                sharedPreferences.getBoolean(KEY_ISDISABLED, false),
                pushToken
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }

    // this method will get the firebase Token
    public String extractFirebaseToken(){
        // firebase Token Id
        String pushToken = "";
        return pushToken;
    }
}
