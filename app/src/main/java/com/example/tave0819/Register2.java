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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Register2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user_life_cycle";
    private static final String ARG_PARAM2 = "user_is_multicultural";
    private static final String ARG_PARAM3 = "user_is_one_parent";
    private static final String ARG_PARAM4 = "user_is_disabled";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String[] life_cycle_items = {"영유아","아동, 청소년", "청년", "중,장년", "노년"};
    String life_cycle;

    RadioGroup RG_is_multicultural;
    RadioButton rb_is_multicultural_true;
    RadioButton rb_is_multicultural_false;

    RadioGroup RG_family_state;
    RadioButton rb_family_state_true;
    RadioButton rb_family_state_false;

    RadioGroup RG_is_disabled;
    RadioButton rb_is_disabled_true;
    RadioButton rb_is_disabled_false;

    // 추후 관심주제 추가


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
                                        int user_is_one_parent, int user_is_disabled) {
        Register2 fragment = new Register2();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, user_life_cycle);
        args.putInt(ARG_PARAM2, user_is_multicultural);
        args.putInt(ARG_PARAM3, user_is_one_parent);
        args.putInt(ARG_PARAM4, user_is_disabled);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register2, container, false);

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

        Button btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }
}