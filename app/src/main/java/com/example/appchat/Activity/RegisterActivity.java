package com.example.appchat.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    //views
    private TextInputEditText tip_RegisterEmail;
    private TextInputEditText tip_RegisterPass;
    private TextInputEditText tip_Re_RegisterPass;
    private Button btnRegister;
    private TextView tv_Login;
    //ProProgressDialog display while registering user
    ProgressDialog registerDialog;

    //Declare an instance of firebaseAuth
    private FirebaseAuth mAuth;
    //Declare a TAG
    private static final String TAG ="RegisterUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Initialize views
        initUI();

        //Initialize ProgressDialog
        registerDialog = new ProgressDialog(this);
        registerDialog.setMessage("Đang đăng ký...");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //handle register button click
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = tip_RegisterEmail.getText().toString().trim();
                String password = tip_RegisterPass.getText().toString().trim();
                String re_password = tip_Re_RegisterPass.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    tip_RegisterEmail.setError("Email không đúng định dạng");
                    tip_RegisterEmail.setFocusable(true);
                }else if(password.length() < 6){
                    tip_RegisterPass.setError("Mật khẩu phải lớn hơn 6 ký tự");
                    tip_RegisterPass.setFocusable(true);
                }else if(!password.equals(re_password)){
                    tip_RegisterPass.setError("Mật khẩu không đúng vui long kiểm tra lại !");
                    tip_Re_RegisterPass.setError("Mật khẩu không đúng vui long kiểm tra lại !");
                    tip_RegisterPass.setFocusable(true);
                }else {
                    registerUser(email, password);
                }
            }
        });
        //handle login activity textview click
        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
    private void initUI() {
        tip_RegisterEmail =findViewById(R.id.tv_RegisterEmail);
        tip_RegisterPass = findViewById(R.id.tv_RegisterPassword);
        tip_Re_RegisterPass = findViewById(R.id.tv_Re_RegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tv_Login = findViewById(R.id.tv_Login);
    }


    private void registerUser(String email, String password) {
        //show progressDialog
        registerDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //disable progressDialog
                            registerDialog.dismiss();
                            // Register success
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        } else {

                            //disable progressDialog
                            registerDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thất bại.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //disable progressDialog
                registerDialog.dismiss();
                Log.w(TAG, "createUserWithEmail:failure", e);
                Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}