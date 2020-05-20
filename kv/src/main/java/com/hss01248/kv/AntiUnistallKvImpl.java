package com.hss01248.kv;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * by hss
 * data:2020-05-20
 * desc:
 */
public class AntiUnistallKvImpl implements IKV {
    SpKvImpl spKv = new SpKvImpl();
    File dir;

    @Override
    public void saveJson(String json, boolean encrypt) {
        spKv.saveJson(json, encrypt);
        File file = getFile(encrypt);
        if (file == null) {
            return;
        }
        writeFile(json, file);

    }


    @Override
    public String getJson(boolean encrypt) {
        String json = spKv.getJson(encrypt);
        if (!TextUtils.isEmpty(json)) {
            return json;
        }
        File file = getFile(encrypt);
        if (file == null || !file.exists()) {
            return "";
        }
        json = readFile(file);
        if (!TextUtils.isEmpty(json)) {
            //sp里没有,而文件里有,那么复制文件内容到sp
            spKv.saveJson(json, encrypt);
        }
        return json;
    }

    private String readFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String str = null;
            StringBuilder sb = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }


    }

    private void writeFile(String json, File file) {
        try {
            file.getParentFile().mkdirs();
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    File getFile(boolean encrypt) {
        if (!hasStoragePermission()) {
            return null;
        }
        if (dir != null) {
            return new File(dir, "uisi" + encrypt);
        }
        String path = KvUtil.secrectPath;
        if (TextUtils.isEmpty(path)) {
            dir = new File(Environment.getExternalStorageDirectory(), "/buyu/jiui/09jl/jjis/iihi/");
        } else {
            dir = new File(path);
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, "uisi" + encrypt);

    }

    public static boolean hasStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return KvUtil.app.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return Environment.getExternalStorageDirectory().canWrite();
        }
    }


}
