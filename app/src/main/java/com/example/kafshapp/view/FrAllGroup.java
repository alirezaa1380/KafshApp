package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;


public class FrAllGroup extends Fragment {
    String temp;
    RetrofitRequast requast;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_all_group, container, false);

        return view;
    }
}