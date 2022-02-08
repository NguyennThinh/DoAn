package com.example.appchat.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appchat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //views
    private TextInputEditText tip_Email;
    private TextInputEditText tip_Pass;
    private TextView tvRegister;
    private Button btnLogin;
    private TextView tvForgotPass;

    //ProProgressDialog display while registering user
    ProgressDialog loginDialog;

    //Declare an instance of firebaseAuth
    private FirebaseAuth mAuth;
    //Declare a TAG
    private static final String TAG ="LoginUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init views
        initUI();

        //Initialize ProgressDialog
        loginDialog = new ProgressDialog(this);
        loginDialog.setMessage("Đang đăng nhập...");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Handle textview register click
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //Handle button Login click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input value
                String email = tip_Email.getText().toString().trim();
                String password = tip_Pass.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //invalid email pattern error
                    tip_Email.setError("Email không đúng định dạng");
                    tip_Email.setFocusable(true);
                }else if(password.length() < 6){
                    //invalid password pattern error
                    tip_Pass.setError("Mật khẩu phải lớn hơn 6 ký tự");
                    tip_Pass.setFocusable(true);
                }else {
                    //User login
                    loginUser(email, password);
                }
            }
        });
        //Forgot pass textview click
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForgotPass();
            }
        });
    }


    private void initUI() {
        tip_Email = findViewById(R.id.tv_LoginEmail);
        tip_Pass = findViewById(R.id.tv_LoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tv_Register);
        tvForgotPass = findViewById(R.id.tv_ForgotPass);
    }

    private void loginUser(String email, String password) {
        loginDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            loginDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginDialog.dismiss();
                Log.w(TAG, "signInWithEmail:failure", e);
                Toast.makeText(LoginActivity.this, "Đăng ký tài khoản thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialogForgotPass() {
        //Alertdialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quên mật khẩu");
        //setLayout Dialog
        LinearLayout layout = new LinearLayout(this);

        //set views in dialog
        EditText edtEmail = new EditText(this);
        edtEmail.setHint("email");
        edtEmail.setMinEms(15);

        layout.addView(edtEmail);
        layout.setPadding(10,10,10,0);

        builder.setView(layout);
        //button  submit
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = edtEmail.getText().toString().trim();
                startChangePass(email);
            }
        });
        //button cancel
        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //disable dialog
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void startChangePass(String email) {
        loginDialog.setMessage("Đang gửi...");
        loginDialog.show();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    loginDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Đã gửi liên kết", Toast.LENGTH_SHORT).show();
                }else{
                    loginDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Gửi thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginDialog.dismiss();
                Log.e(TAG, e.toString());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}