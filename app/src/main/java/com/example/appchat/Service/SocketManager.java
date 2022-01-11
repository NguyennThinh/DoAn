package com.example.appchat.Service;

import android.content.Context;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketManager {
    private static SocketManager instance;
    private static final String SERVER_ADDRESS = "http://192.168.1.7:3000";
    private Socket mSocket;
    private Context context;

    public SocketManager(Context context) {
        this.context = context;
        this.mSocket = getServerSocket();
    }

    public static SocketManager get(Context context){
        if(instance == null){
            instance = getSync(context);
        }
        instance.context = context;
        return instance;
    }

    private static synchronized SocketManager getSync(Context context) {
        if(instance == null){
            instance = new SocketManager(context);
        }
        return instance;
    }

    public Socket getSocket(){
        return this.mSocket;
    }

    public Socket getServerSocket() {
        try {
         //   IO.Options opts = new IO.Options();
         //   opts.forceNew = true;
          //  opts.reconnection = true;
            mSocket = IO.socket(SERVER_ADDRESS);
            return mSocket;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
