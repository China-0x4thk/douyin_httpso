package com.heyaug.httpso;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.heyaug.httpso.Service.MyService;

import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "NigthTeam";
    private int version = Build.VERSION.SDK_INT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.id_tx_index);

        try {
            InetAddress inetAddress = NetUtils.getLocalIPAddress();
            if (inetAddress != null){
                textView.setText(inetAddress.getHostAddress() + "  version: " + version);
            }else {
                textView.setText("未获取到inetAddress" + "  version: " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void operate(View view) {
        switch (view.getId()){
            case R.id.id_bt_index:
                //启动服务:创建-->启动-->销毁
                //如果服务已经创建了，后续重复启动，操作的都是同一个服务，不会再重新创建了，除非你先销毁它
                Intent it1 = new Intent(this, MyService.class);
                Log.d(TAG, "operate: button");
                startService(it1);
                ((Button) view).setText("服务已开启");
                break;
        }
    }
}
