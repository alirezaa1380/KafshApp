package com.example.kafshapp.view;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kafshapp.model.ApiClient;
import com.example.kafshapp.model.Person;
import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;
import com.example.kafshapp.model.ShearedPerfernce;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrSingUp extends Fragment {
    EditText editTextName,editTextPhone,editTextPass;
    Button buttonSave;
    RetrofitRequast requast;
    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fr_sing_up, container, false);
        Initialize(view);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new SpotsDialog(getContext(), R.style.Custom);
                progressDialog.show();
                CheckDataPerson();
            }});



        return view;
    }


    public void Initialize(View view){
        buttonSave=view.findViewById(R.id.btn_save_singup);
        editTextName=view.findViewById(R.id.edt_name_singup);
        editTextPhone=view.findViewById(R.id.edt_phone_singup);
        editTextPass=view.findViewById(R.id.edt_pass_singup);
    }


    //SingUp person in table sql
    public void CheckDataPerson(){
        if (editTextPass.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "لطفا رمز عبور را وارد کنید", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }else if (editTextPhone.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "لطفا شماره تلفن خود را وارد کنید", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

        else if (editTextName.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "لطفا نام خود را وارد کنید", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

        else if (editTextPhone.getText().length()<11){
            Toast.makeText(getContext(), "شماره تلفن را به صورت صحیح واد کنید", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

        else if (editTextPass.getText().toString().length()<8){
            Toast.makeText(getContext(), "حداقل کاراکتر رمز 8 است", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        else {
            Person person=new Person();
            person.setNamePerson(editTextName.getText().toString());
            person.setPhonePerson(editTextPhone.getText().toString());
            person.setPasswordPerson(editTextPass.getText().toString());
            requast= ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
            requast.post(person).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(getContext(), "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
                    shearedPerfernce.SaveUser(editTextName.getText().toString(),editTextPass.getText().toString(),editTextPhone.getText().toString());
                    Navigation.findNavController(getView()).navigate(R.id.action_frSingUp_to_frHomePage);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
                            String name=shearedPerfernce.getFirstName();
                            Notification notification=new NotificationCompat.Builder(getContext(),"myapp")
                                    .setSmallIcon(R.mipmap.logo1)
                                    .setContentTitle("کاربر"+" "+name+" "+"به اپلیکیشن کفش آپ خوش آمدید")
                                    .build();

                            NotificationManager manager= (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            manager.notify(100,notification);
                        }
                    },5000);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(getContext(), "خطا در شبکه", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }

    }
}