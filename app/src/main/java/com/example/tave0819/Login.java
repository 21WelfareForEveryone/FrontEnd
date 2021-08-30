package com.example.tave0819;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "ID";
    private static final String ARG_PARAM2 = "Password";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // value from edit text
    private EditText et_id;
    private EditText et_pwd;

    private View view;

    public Login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static Login newInstance(String param1, String param2) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // get values from fragment - > activity(ID)
    public String getID(){
        return getArguments().getString(ARG_PARAM1);
    }
    // get values from fragment - > activity(PWD)
    public String getPwd(){
        return getArguments().getString(ARG_PARAM2);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_login_to_register1);
            }
        });

        et_id = (EditText) view.findViewById(R.id.et_id);
        et_pwd = (EditText) view.findViewById(R.id.et_pwd);

        Log.v("id input complete: ", "id input complete");
        Log.v("pwd input  complete: ", "pwd input complete");

        Button btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(getActivity(), LoginActivity.class);
                //startActivity(intent);

                userLogin();

                // after login process succeed
                Bundle bundle = new Bundle();
                bundle.putString("fragment_home", "test");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment home = new HomeFragment();
                home.setArguments(bundle);
                transaction.replace(R.id.nav_host_fragment, home);
                transaction.commit(); // 저장

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_login_to_homeFragment);

            }
        });
        // Inflate the layout for this fragment
        return view;
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
                        Toast.makeText(getActivity().getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonRequest);
        Log.v("VolleySingleton get Instance ", "complete");
    }
}