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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class Register2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user_life_cycle";
    private static final String ARG_PARAM2 = "user_is_multicultural";
    private static final String ARG_PARAM3 = "user_is_one_parent";
    private static final String ARG_PARAM4 = "user_is_disabled";
    private static final String ARG_PARAM5 = "user_interest";

    // TODO: Rename and change types of parameters
    private int  mParam1;
    private int  mParam2;
    private int  mParam3;
    private int  mParam4;
    private int  mParam5;

    // Register1 params
    static String user_name;
    static String user_id;
    static String user_password;
    static int user_gender;
    static int user_income;
    static String user_address;


    String[] life_cycle_items = {"영유아","아동, 청소년", "청년", "중,장년", "노년"};
    Spinner spinner_life_cycle;
    int life_cycle;

    RadioGroup RG_is_multicultural;
    RadioButton rb_is_multicultural_true;
    RadioButton rb_is_multicultural_false;
    int is_multicultural;

    RadioGroup RG_family_state;
    RadioButton rb_family_state_true;
    RadioButton rb_family_state_false;
    int family_state;

    RadioGroup RG_is_disabled;
    RadioButton rb_is_disabled_true;
    RadioButton rb_is_disabled_false;
    int is_disabled;

    RadioGroup RG_interest;
    RadioButton rb_interest0;
    RadioButton rb_interest1;
    RadioButton rb_interest2;
    RadioButton rb_interest3;
    RadioButton rb_interest4;
    RadioButton rb_interest5;
    RadioButton rb_interest6;
    Bundle variable_register1 = new Bundle();
    int interest;

    // register variable for fragment_register2
    private View view;

    public Register2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Register2.
     */

    // TODO: Rename and change types and number of parameters
    public static Register2 newInstance( int user_life_cycle, int user_is_multicultural,
                                        int user_is_one_parent, int user_is_disabled, int user_interest) {
        Register2 fragment = new Register2();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, user_life_cycle);
        args.putInt(ARG_PARAM2, user_is_multicultural);
        args.putInt(ARG_PARAM3, user_is_one_parent);
        args.putInt(ARG_PARAM4, user_is_disabled);
        args.putInt(ARG_PARAM5, user_interest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null){
            user_name = getArguments().getString("user_name");
            user_id = getArguments().getString("user_id");
            user_password = getArguments().getString("user_password");
            user_gender = getArguments().getInt("user_gender");
            user_income = getArguments().getInt("user_income");
            user_address = getArguments().getString("user_address");
        }
    }

    // savedInstanceState : activity 종료 후에도 값을 메모리에 저장한다.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register2, container, false);

        Log.v("id input for register1: ", "id:"+ user_id);
        Log.v("pwd input for register1: ", "pwd:" + user_password);
        Log.v("name input for register1: ", "name:" + user_name);
        Log.v("gender input for register1: ", "gender:" + user_gender);
        Log.v("income input for register1: ", "income:" + user_income);
        Log.v("address input for register1: ", "address:" + user_address);

        /*
        variable_register1.putString("user_id", user_id);
        variable_register1.putString("user_password", user_password);
        variable_register1.putString("user_name",user_name);
        variable_register1.putInt("user_gender", user_gender);
        variable_register1.putInt("user_income", user_income);
        variable_register1.putString("user_address", user_address);
         */


        // register2 variable
        RG_is_multicultural = (RadioGroup)view.findViewById(R.id.isMultiCultureRadioGroup);
        rb_is_multicultural_true = (RadioButton)view.findViewById(R.id.btn_isMultiCulture_true);
        rb_is_multicultural_false = (RadioButton)view.findViewById(R.id.btn_isMultiCulture_false);

        RG_is_disabled = (RadioGroup)view.findViewById(R.id.isDisabledRadioGroup);
        rb_is_disabled_true = (RadioButton)view.findViewById(R.id.btn_isDisabled_true);
        rb_is_disabled_false = (RadioButton)view.findViewById(R.id.btn_isDisabled_false);

        RG_family_state = (RadioGroup)view.findViewById(R.id.familyStateRadioGroup);
        rb_family_state_true = (RadioButton)view.findViewById(R.id.btn_family_state_true);
        rb_family_state_false = (RadioButton)view.findViewById(R.id.btn_family_state_false);

        RG_interest = (RadioGroup)view.findViewById(R.id.interestRadioGroup);
        rb_interest0 = (RadioButton)view.findViewById(R.id.rb_interest0);
        rb_interest1 = (RadioButton)view.findViewById(R.id.rb_interest1);
        rb_interest2 = (RadioButton)view.findViewById(R.id.rb_interest2);
        rb_interest3 = (RadioButton)view.findViewById(R.id.rb_interest3);
        rb_interest4 = (RadioButton)view.findViewById(R.id.rb_interest4);
        rb_interest5 = (RadioButton)view.findViewById(R.id.rb_interest5);
        rb_interest6 = (RadioButton)view.findViewById(R.id.rb_interest6);


        spinner_life_cycle = (Spinner)view.findViewById(R.id.spinner_life_cycle);

        // life_cycle with spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, life_cycle_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_life_cycle.setAdapter(adapter);
        spinner_life_cycle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                life_cycle = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                life_cycle = -1;
            }
        });

        Button btn_previous = (Button) view.findViewById(R.id.btn_previous);
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("fragment_register1", "test");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Register1 register1 = new Register1();
                register1.setArguments(bundle);
                transaction.replace(R.id.nav_host_fragment, register1);
                transaction.commit(); // 저장

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_register2_to_register1);
            }
        });
        
        // register
        Button btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                user_id = variable_register1.getString("user_id");
                user_password = variable_register1.getString("user_password");
                user_name = variable_register1.getString("user_name");
                user_gender = variable_register1.getInt("user_gender");
                user_income = variable_register1.getInt("user_income");
                user_address = variable_register1.getString("user_address");

                 */

                if(RG_is_multicultural.getCheckedRadioButtonId() == rb_is_multicultural_true.getId()){
                    is_multicultural = 1;
                }
                else if(RG_is_multicultural.getCheckedRadioButtonId() == rb_is_multicultural_false.getId()){
                    is_multicultural = 0;
                }

                if(RG_is_disabled.getCheckedRadioButtonId() == rb_is_disabled_true.getId()){
                    is_disabled = 1;
                }
                else if(RG_is_disabled.getCheckedRadioButtonId() == rb_is_disabled_false.getId()) {
                    is_disabled = 0;
                }

                if(RG_family_state.getCheckedRadioButtonId() == rb_family_state_true.getId()){
                    family_state = 1;
                }
                else if(RG_family_state.getCheckedRadioButtonId() == rb_family_state_false.getId()) {
                    family_state = 0;
                }

                life_cycle = spinner_life_cycle.getSelectedItemPosition();

                if(RG_interest.getCheckedRadioButtonId() == rb_interest0.getId()){
                    interest = 0;
                }
                else if(RG_interest.getCheckedRadioButtonId() == rb_interest1.getId()){
                    interest = 1;
                }
                else if(RG_interest.getCheckedRadioButtonId() == rb_interest2.getId()){
                    interest = 2;
                }
                else if(RG_interest.getCheckedRadioButtonId() == rb_interest3.getId()){
                    interest = 3;
                }
                else if(RG_interest.getCheckedRadioButtonId() == rb_interest4.getId()){
                    interest = 4;
                }
                else if(RG_interest.getCheckedRadioButtonId() == rb_interest5.getId()){
                    interest = 5;
                }
                else if(RG_interest.getCheckedRadioButtonId() == rb_interest6.getId()){
                    interest = 6;
                }

                // 여기서 register1 변수 호출이 되질 않는다...
                Log.v("user_name_check_before_register", "user_name: " + user_name);
                Log.v("user_id_check_before_register","user_id: " + user_id);
                Log.v("user_password_check_before_register","user_password: " + user_password);
                Log.v("user_gender_check_before_register", "user_gender: " + user_gender);
                Log.v("user_income_check_before_register", "user_income: " + user_income);
                Log.v("user_address_check_before_register", "user_address: " + user_address);
                Log.v("user_life_cycle_check_before_register", "user_life_cycle: " + life_cycle);
                Log.v("user_is_multicultural_check_before_register", "user_is_multicultural: " + is_multicultural);
                Log.v("user_is_one_parent_check_before_register", "user_is_one_parent: " + family_state);
                Log.v("user_is_disabled_check_before_register", "user_is_disabled: " + is_disabled);
                Log.v("user_interest_check_before_register", "user_interest: " + interest);
                Log.v("user_mToken_check_before_register", "user_mToken: " + "");

                registerUser();

                Bundle bundle = new Bundle();
                bundle.putString("fragment_login", "test");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Login login = new Login();
                login.setArguments(bundle);
                transaction.replace(R.id.nav_host_fragment,login);
                transaction.commit(); // 저장
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_register2_to_login);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void registerUser(){

        // 반영이 안됨..
        if(RG_is_multicultural.getCheckedRadioButtonId() == rb_is_multicultural_true.getId()){
            is_multicultural = 1;
        }
        else if(RG_is_multicultural.getCheckedRadioButtonId() == rb_is_multicultural_false.getId()){
            is_multicultural = 0;
        }

        if(RG_is_disabled.getCheckedRadioButtonId() == rb_is_disabled_true.getId()){
            is_disabled = 1;
        }
        else if(RG_is_disabled.getCheckedRadioButtonId() == rb_is_disabled_false.getId()) {
            is_disabled = 0;
        }

        if(RG_family_state.getCheckedRadioButtonId() == rb_family_state_true.getId()){
            family_state = 1;
        }
        else if(RG_family_state.getCheckedRadioButtonId() == rb_family_state_false.getId()) {
            family_state = 0;
        }

        life_cycle = spinner_life_cycle.getSelectedItemPosition();

        if(RG_interest.getCheckedRadioButtonId() == rb_interest0.getId()){
            interest = 0;
        }
        else if(RG_interest.getCheckedRadioButtonId() == rb_interest1.getId()){
            interest = 1;
        }
        else if(RG_interest.getCheckedRadioButtonId() == rb_interest2.getId()){
            interest = 2;
        }
        else if(RG_interest.getCheckedRadioButtonId() == rb_interest3.getId()){
            interest = 3;
        }
        else if(RG_interest.getCheckedRadioButtonId() == rb_interest4.getId()){
            interest = 4;
        }
        else if(RG_interest.getCheckedRadioButtonId() == rb_interest5.getId()){
            interest = 5;
        }
        else if(RG_interest.getCheckedRadioButtonId() == rb_interest6.getId()){
            interest = 6;
        }

        int user_life_cycle = life_cycle;
        int user_is_multicultural = is_multicultural;
        int user_is_one_parent = family_state;
        int user_is_disabled = is_disabled;
        int user_interest = interest;
        String user_mToken = "";

        // log list for variable request check
        Log.v("user_name_check", "user_name: " + user_name);
        Log.v("user_id_check","user_id: " + user_id);
        Log.v("user_password_check","user_password: " + user_password);
        Log.v("user_gender_check", "user_gender: " + user_gender);
        Log.v("user_income_check", "user_income: " + user_income);
        Log.v("user_address_check", "user_address: " + user_address);
        Log.v("user_life_cycle_check", "user_life_cycle: " + user_life_cycle);
        Log.v("user_is_multicultural_check", "user_is_multicultural: " + user_is_multicultural);
        Log.v("user_is_one_parent_check", "user_is_one_parent: " + user_is_one_parent);
        Log.v("user_is_disabled_check", "user_is_disabled: " + user_is_disabled);
        Log.v("user_interest_check", "user_interest: " + user_interest);
        Log.v("user_mToken_check", "user_mToken: " + user_mToken);

        // Register Request
        JSONObject params = new JSONObject();

        try{
            params.put("user_id", user_id);
            params.put("user_password", user_password);
            params.put("user_name", user_name);
            params.put("user_gender", user_gender);
            params.put("user_income", user_income);
            params.put("user_address",user_address);
            params.put("user_life_cycle", user_life_cycle);
            params.put("user_is_multicultural", user_is_multicultural);
            params.put("user_is_one_parent", user_is_one_parent);
            params.put("user_is_disabled", user_is_disabled);
            params.put("user_interest", user_interest);
            params.put("user_mToken", user_mToken);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URLs.url_register, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(!response.getBoolean("error")){
                        Toast.makeText(getActivity().getApplicationContext(),response.getString("message"), Toast.LENGTH_SHORT).show();

                        // getting the user from the response
                        JSONObject userJson = response.getJSONObject("user");
                        Log.v("userJson: ", userJson.getString("user_name"));

                        //User user = new User();
                        //SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        
                        Toast.makeText(getActivity().getApplicationContext(), "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
        };
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}