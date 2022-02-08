package com.example.appchat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.appchat.Fragment.ChatFragment;
import com.example.appchat.Fragment.FriendFragment;
import com.example.appchat.Fragment.GroupFragment;
import com.example.appchat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //views
    private MeowBottomNavigation bottomNavigation;

    //Declare an instance of firebaseAuth
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        initUI();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //init MeowBottomNavigation
        bottomNavigation = findViewById(R.id.Nav_Bottom);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_message));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_friend));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_group));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment =null;
                switch (item.getId()){
                    case 1:
                        fragment= new ChatFragment();
                        break;
                    case 2:
                        fragment= new FriendFragment();
                        break;
                    case 3:
                        fragment= new GroupFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout, fragment )
                        .commit();
            }
        });
        bottomNavigation.show(1,true);

        //Handle MeowBottomNavigation click menu
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You click:"+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You reselected:"+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //init views
    private void initUI() {

    }
    private void checkLoginStatus() {
        FirebaseUser user = mAuth.getCurrentUser();
        if(user !=null){
            Toast.makeText(this, "Đã có user đăng nhập", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(new Intent( this, WelcomeActivity.class));
        }
    }



    @Override
    protected void onStart() {
        checkLoginStatus();
        super.onStart();
    }


}