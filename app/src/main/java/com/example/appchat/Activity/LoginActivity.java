package com.example.appchat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appchat.R;
import com.example.appchat.Service.SocketManager;

import io.socket.client.Socket;

public class LoginActivity extends AppCompatActivity {
    private Socket mSocket;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSocket= SocketManager.get(this).getSocket();
        mSocket.connect();

        addControl();
        addEvent();
    }

    private void addControl() {
        btnLogin  =findViewById(R.id.btnLogin);
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSocket.emit("test","Ok");
            }
        });
    }
}