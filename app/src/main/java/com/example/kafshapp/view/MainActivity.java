package com.example.kafshapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.kafshapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build bottom navigation
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        NavController controller= Navigation.findNavController(this,R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,controller);


        // delete bottom navigation from page logIn,singUp,comment
        controller.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId()==R.id.frSingUp){
                        bottomNavigationView.setVisibility(View.GONE);
                }else if (destination.getId()==R.id.frLogIn){
                       bottomNavigationView.setVisibility(View.GONE);
                }else if (destination.getId()==R.id.frComment){
                    bottomNavigationView.setVisibility(View.GONE);
                }else {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                }

            }
        });

    }


}