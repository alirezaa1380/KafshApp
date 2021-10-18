package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kafshapp.adapter.AdapterViewPager;
import com.example.kafshapp.R;
import com.example.kafshapp.model.ShearedPerfernce;


public class FrProfile extends Fragment {
    ImageView imageViewSetting,imageViewNottification;
    ViewPager viewPager;
    TextView textViewName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fr_profile, container, false);

        Initialize(view);
        SetUpViewPager();

        return view;
    }

    public void Initialize(View view){
        imageViewSetting=view.findViewById(R.id.imv_setting);
        imageViewNottification=view.findViewById(R.id.imv_nottification);
        viewPager=view.findViewById(R.id.view_pager);
        textViewName=view.findViewById(R.id.textViewNameProfile);
    }

    public void SetUpViewPager(){

        AdapterViewPager adapter=new AdapterViewPager(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                imageViewSetting.setImageResource(R.drawable.setting_profile_p);
                imageViewNottification.setImageResource(R.drawable.notification_profile);
            }
        });

        imageViewNottification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                imageViewNottification.setImageResource(R.drawable.notification_profile_p);
                imageViewSetting.setImageResource(R.drawable.setting_profile);
            }
        });

        textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                imageViewNottification.setImageResource(R.drawable.notification_profile);
                imageViewSetting.setImageResource(R.drawable.setting_profile);
            }
        });
        ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
        textViewName.setText(shearedPerfernce.getFirstName());

    }
}