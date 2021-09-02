package com.example.tave0819;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user_id";
    private static final String ARG_PARAM2 = "user_password";
    private static final String ARG_PARAM3 = "user_name";
    private static final String ARG_PARAM4 = "user_gender";
    private static final String ARG_PARAM5 = "user_address";
    private static final String ARG_PARAM6 = "user_life_cycle";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
    private String mParam6;

    EditText et_username;
    EditText et_id;
    EditText et_pwd;

    String[] income_items = {"1분위", "2분위", "3분위", "4분위", "5분위", "6분위", "7분위", "8분위","9분위"};
    int Income;

    String[] city_items = {"서울특별시"};
    String[] local_items = {"강북구","강서구", "강동구", "강남구", "마포구", "영등포구", "관악구", "종로구", "노원구"};
    String City;
    String Local;
    String Address;

    // boolean type variables
    RadioGroup RG_gender;
    RadioButton rb_male;
    RadioButton rb_female;
    int Gender;

    // spinner type variables
    Spinner spinner_income;

    Spinner spinner_address_city;
    Spinner spinner_address_gu;

    private View view;

    public Register1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Register1.
     */
    // TODO: Rename and change types and number of parameters
    public static Register1 newInstance(String user_id, String user_password, String user_name,
                                        int user_gender, String user_address) {
        Register1 fragment = new Register1();
        Bundle args = new Bundle();
        args.putString("user_id", user_id);
        args.putString("user_password", user_password);
        args.putString("user_name", user_name);
        args.putInt("user_gender", user_gender);
        args.putString("user_address", user_address);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_register1, container, false);

        // manage user variable
        et_username = (EditText) view.findViewById(R.id.et_username);
        et_id = (EditText)view.findViewById(R.id.et_id);
        et_pwd = (EditText)view.findViewById(R.id.et_pwd);

        // gender
        rb_male =  (RadioButton)view.findViewById(R.id.btn_male);
        rb_female = (RadioButton)view.findViewById(R.id.btn_female);
        RG_gender = (RadioGroup)view.findViewById(R.id.genderRadioGroup);

        // spinner(소득분위)
        spinner_income = (Spinner)view.findViewById(R.id.spinner_income);

        // spinner(주소)
        spinner_address_city = (Spinner)view.findViewById(R.id.spinner_address_city);
        spinner_address_gu = (Spinner)view.findViewById(R.id.spinner_address_gu);


        // income preprocessing
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, income_items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_income.setAdapter(adapter);
        spinner_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Income = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Income = -1;
            }
        });

        // address preprocessing
        ArrayAdapter<String> adapter_address_city = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, city_items);
        adapter_address_city.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_address_city.setAdapter(adapter_address_city);
        spinner_address_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City = city_items[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                City = (String) city_items[0];
            }
        });

        ArrayAdapter<String> adapter_address_gu = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, local_items);
        adapter_address_gu.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_address_gu.setAdapter(adapter_address_gu);
        spinner_address_gu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Local = (String) local_items[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Local = local_items[0];
            }
        });

        Button btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gender preprocessing
                if(RG_gender.getCheckedRadioButtonId() == rb_male.getId()){
                    Log.v("rb_male is selected? : ", "true");
                    Gender = 1;
                }
                else if(RG_gender.getCheckedRadioButtonId() == rb_female.getId()) {
                    Log.v("rb_female is selected? : ", "true");
                    Gender = 0;
                }
                
                Income = spinner_income.getSelectedItemPosition();

                // address
                City = spinner_address_city.getSelectedItem().toString();
                Local = spinner_address_gu.getSelectedItem().toString();
                Address = City +"-"+ Local;

                Log.v("id input for register: ", "id input complete"+ et_id.getText().toString());
                Log.v("pwd input for register: ", "pwd input complete" + et_pwd.getText().toString());
                Log.v("name input for register: ", "name input complete" + et_username.getText().toString());
                Log.v("gender input for register: ", "gender input complete" + Gender);
                Log.v("income input for register: ", "income input complete" + Income);
                Log.v("address input for register: ", "address input complete" + Address);


                Bundle bundle = new Bundle();
                bundle.putString("user_id", et_id.getText().toString().trim());
                bundle.putString("user_password", et_pwd.getText().toString());
                bundle.putString("user_name", et_username.getText().toString());
                bundle.putInt("user_gender", Gender);
                bundle.putInt("user_income", Income);
                bundle.putString("user_address", Address);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Register2 register2 = new Register2();
                register2.setArguments(bundle);
                transaction.replace(R.id.nav_host_fragment, register2);
                transaction.commit(); // 저장

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_register1_to_register2);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}