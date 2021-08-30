package com.example.tave0819;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    // boolean type variables
    RadioGroup RG_gender;
    RadioButton rb_male;
    RadioButton rb_female;

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

        Button btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("fragment_register2", "test");
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