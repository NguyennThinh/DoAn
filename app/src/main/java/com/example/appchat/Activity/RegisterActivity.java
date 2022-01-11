package com.example.appchat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appchat.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
        private TextInputEditText ipPhone;
        private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initUI();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), ipPhone.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUI() {
        ipPhone =findViewById(R.id.tv_RegisterPhone);
        btnRegister = findViewById(R.id.btnRegister);

    }
}