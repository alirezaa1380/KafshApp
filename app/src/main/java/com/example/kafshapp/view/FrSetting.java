package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kafshapp.R;
import com.example.kafshapp.view.FrHarim;

public class FrSetting extends Fragment {

    TextView textViewView
            ,textViewExit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_setting, container, false);
        Initialize(view);
        textViewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction  fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.conss,new FrHarim());
                fragmentTransaction.commit();
            }
        });

        textViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_frProfile_to_navigation2);
            }
        });


        return view;
    }

    private void Initialize(View view) {
        textViewView=view.findViewById(R.id.txt_view_harim);
        textViewExit=view.findViewById(R.id.txt_exit);
    }

}