package com.example.kafshapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kafshapp.R;
import com.example.kafshapp.model.ShearedPerfernce;

public class FrAcount extends Fragment {
    TextView textViewname
            ,textViewphone
            ,textViewaddress;

    TextView textViewChangeName
            ,textViewChangePhone
            ,textViewChangeAddress;

    TextView textViewSaveName
            ,textViewSavePhone
            ,textViewSaveAddress;

    EditText editTextName
            ,editTextPhone
            ,editTextAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr_acount, container, false);

        Initialize(view);
        GetDataFromSharedPerfernce();

        return view;
    }

    private void GetDataFromSharedPerfernce() {
        ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
        textViewname.setText(shearedPerfernce.getFirstName());
        textViewphone.setText(shearedPerfernce.getPhone());
        textViewaddress.setText(shearedPerfernce.getAddress());

        textViewChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setVisibility(View.VISIBLE);
                textViewChangeName.setVisibility(View.INVISIBLE);
                textViewSaveName.setVisibility(View.VISIBLE);
            }
        });

        textViewChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPhone.setVisibility(View.VISIBLE);
                textViewChangePhone.setVisibility(View.INVISIBLE);
                textViewSavePhone.setVisibility(View.VISIBLE);
            }
        });


        textViewChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAddress.setVisibility(View.VISIBLE);
                textViewChangeAddress.setVisibility(View.INVISIBLE);
                textViewSaveAddress.setVisibility(View.VISIBLE);
            }
        });






        textViewSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewSaveName.setVisibility(View.INVISIBLE);
                editTextName.setVisibility(View.INVISIBLE);
                textViewChangeName.setVisibility(View.VISIBLE);
                shearedPerfernce.SaveName(editTextName.getText().toString());
                textViewname.setText(editTextName.getText().toString());


            }
        });

        textViewSavePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSavePhone.setVisibility(View.INVISIBLE);
                editTextPhone.setVisibility(View.INVISIBLE);
                textViewChangePhone.setVisibility(View.VISIBLE);
                shearedPerfernce.SavePhone(editTextPhone.getText().toString());
                textViewphone.setText(editTextPhone.getText().toString());
            }
        });

        textViewSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewSaveAddress.setVisibility(View.INVISIBLE);
                editTextAddress.setVisibility(View.INVISIBLE);
                textViewChangeAddress.setVisibility(View.VISIBLE);
                shearedPerfernce.SaveAddress(editTextAddress.getText().toString());
                textViewaddress.setText(editTextAddress.getText().toString());
            }
        });

    }

    private void Initialize(View view) {
        textViewname=view.findViewById(R.id.txt_name_account);
        textViewphone=view.findViewById(R.id.txt_phone_account);
        textViewaddress=view.findViewById(R.id.txt_adress_account);

        textViewChangeName=view.findViewById(R.id.btn_cheng_name_account);
        textViewChangePhone=view.findViewById(R.id.btn_cheng_phone_account);
        textViewChangeAddress=view.findViewById(R.id.btn_cheng_address_account);

        textViewSaveName=view.findViewById(R.id.btn_save_name_account);
        textViewSavePhone=view.findViewById(R.id.btn_save_phone_account);
        textViewSaveAddress=view.findViewById(R.id.btn_save_address_account);

        editTextName=view.findViewById(R.id.edt_name_account);
        editTextPhone=view.findViewById(R.id.edt_phone_account);
        editTextAddress=view.findViewById(R.id.edt_address_account);
    }
}