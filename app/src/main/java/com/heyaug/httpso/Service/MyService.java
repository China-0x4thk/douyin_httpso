package com.heyaug.httpso.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.heyaug.httpso.ServerManager;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyService extends Service {
    private static final String TAG = "NigthTeam";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MyService");
        new Thread() {
            @Override
            public void run() {
                super.run();
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName("0.0.0.0");
                    Log.d(TAG, "onCreate: " + inetAddress.getHostAddress());
                    ServerManager serverManager = new ServerManager(getApplicationContext(), inetAddress, 8888);
                    serverManager.startServer();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}