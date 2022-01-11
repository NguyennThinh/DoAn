package com.example.appchat.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.appchat.Fragment.ChatFragment;
import com.example.appchat.Fragment.FriendFragment;
import com.example.appchat.Fragment.GroupFragment;
import com.example.appchat.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(1,true);
        
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

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout, fragment )
                                    .commit();
    }
}