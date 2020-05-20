package com.hss01248.ikv.android;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.hss01248.kv.KvUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KvUtil.init(getApplication());


        KvUtil.save("dd","test");
        KvUtil.saveWithEncrypt("dd2","test2");

        Log.d("dd",KvUtil.get().optString("dd")+"--val");
        Log.d("dd2",KvUtil.getEncrypted().optString("dd2")+"--val");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},90);
        }
    }
}
