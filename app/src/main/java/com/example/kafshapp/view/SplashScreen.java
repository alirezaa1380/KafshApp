package com.example.kafshapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.example.kafshapp.R;
import com.google.android.material.snackbar.Snackbar;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                registerReceiver(broadcastReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                if (isConnected()){
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                }
                else {
                    ConstraintLayout layout=findViewById(R.id.test);
                    Snackbar snackbar=Snackbar.make(layout,"خطا در اتصال به اینترنت",Snackbar.LENGTH_LONG);
                    snackbar.show();

                }
            }
        },3000);
    }






    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isConnected()){
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }else {

            }
        }
    };



    private boolean isConnected() {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}