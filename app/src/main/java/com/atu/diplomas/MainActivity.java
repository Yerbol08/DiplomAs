package com.atu.diplomas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(this, Choice.class));
        }
        else {
            Toast.makeText(getApplicationContext(), user.getDisplayName() + user.getEmail(), Toast.LENGTH_LONG).show();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                androidx.transition.R.id.transition_layout_save, R.id.addConfFragment, R.id.messageFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.listFragment:
                            Toast.makeText(getApplicationContext(), "Click 1", Toast.LENGTH_LONG).show();
                            openFragment(ListFragment.newInstance());
                            return true;
                        case R.id.addConfFragment:
                            Toast.makeText(getApplicationContext(), "Click 2", Toast.LENGTH_LONG).show();
                            openFragment(AddConfFragment.newInstance());
                            return true;
                        case R.id.messageFragment:
                            Toast.makeText(getApplicationContext(), "Click 3", Toast.LENGTH_LONG).show();
                            openFragment(MessageFragment.newInstance(   ));
                            return true;
                    }
                    return false;
                }
            };

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}