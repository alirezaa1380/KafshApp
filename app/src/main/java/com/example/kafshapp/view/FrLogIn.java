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
import android.widget.TextView;
import android.widget.Toast;

import com.example.kafshapp.model.ApiClient;
import com.example.kafshapp.R;
import com.example.kafshapp.model.RetrofitRequast;
import com.example.kafshapp.model.ShearedPerfernce;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FrLogIn extends Fragment {
    EditText editTextName,editTextPass;
    TextView textViewClickStartPage2
            ,textViewOnclick;
    Button buttonSave;
    AlertDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fr_log_in, container, false);
        Initialize(view);

        textViewOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_frLogIn_to_frSingUp);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new SpotsDialog(getContext(), R.style.Custom);
                progressDialog.show();
                LogInFromServer();

            }
        });


        return view;
    }

    public void Initialize(View view){
        editTextName=view.findViewById(R.id.edt_name_login);
        editTextPass=view.findViewById(R.id.edt_password_login);
        textViewClickStartPage2=view.findViewById(R.id.txt_btn_onclick);
        buttonSave=view.findViewById(R.id.btn_save_login);
        textViewOnclick=view.findViewById(R.id.txt_btn_onclick);

    }

    public void LogInFromServer(){
        RetrofitRequast requast= ApiClient.getRetrofit("https://www.kafshapp.ir/").create(RetrofitRequast.class);
        String a =editTextName.getText().toString();
        String b=editTextPass.getText().toString();
        requast.GetDataPerson(a,b).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("3")){
                    Toast.makeText(getContext(), "با موفقیت وارد شدید", Toast.LENGTH_SHORT).show();
                    ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
                    shearedPerfernce.SaveUser2(editTextName.getText().toString(),editTextPass.getText().toString());
                    Navigation.findNavController(getView()).navigate(R.id.action_frLogIn_to_frHomePage);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ShearedPerfernce shearedPerfernce=new ShearedPerfernce(getContext());
                            String name=shearedPerfernce.getFirstName();
                            Notification notification=new NotificationCompat.Builder(getContext(),"myapp")
                                    .setSmallIcon(R.mipmap.logo1)
                                    .setContentTitle("KAFSHAPP")
                                    .setContentText("کاربر"+" "+name+" "+"به اپلیکیشن کفش آپ خوش آمدید")
                                    .build();

                            NotificationManager manager= (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            manager.notify(100,notification);
                        }
                    },5000);

                }else {
                    Toast.makeText(getContext(), "نام کاربری یا رمز عبور اشتباه است ", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "خطا در شبکه", Toast.LENGTH_SHORT).show();
            }
        });
    }
}