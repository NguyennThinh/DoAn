package com.example.appchat.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appchat.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnSwitchRegister;
    private Button btnSwitchLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //init views
        initUI();

        //handle switch register activity button click
        btnSwitchRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SwitchRegisterActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(SwitchRegisterActivity);
            }
        });
        //handle switch login activity button click
        btnSwitchLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SwitchLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(SwitchLoginActivity);
            }
        });
    }

    private void initUI() {
        btnSwitchRegister = findViewById(R.id.btnSwitchRegister);
        btnSwitchLogin = findViewById(R.id.btnSwitchLogin);
    }
}