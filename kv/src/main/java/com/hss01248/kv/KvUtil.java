package com.hss01248.kv;

import android.app.Application;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * by hss
 * data:2020-05-19
 * desc:
 */
public class KvUtil {

    static JSONObject json;
    static JSONObject encryptedJson;
    static IKV ikv;
    static IEncrypt encrypt;
    static Application app;

    public static void setSecrectPath(String secrectPath) {
        KvUtil.secrectPath = secrectPath;
    }

    static String secrectPath;

    public static void init(Application app){
        KvUtil.ikv = new AntiUnistallKvImpl();
        encrypt = new DefaultEncrypt();
        KvUtil.app = app;
    }

    public static JSONObject get(){
        if(json != null){
            return json;
        }
        String jsonStr =  ikv.getJson(false);
        if(TextUtils.isEmpty(jsonStr)){
            json = new JSONObject();
            return json;
        }
        try {
            json = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
            json = new JSONObject();
        }
        return json;
    }

    public static JSONObject getEncrypted(){
        if(encryptedJson != null){
            return encryptedJson;
        }
        String jsonStr =  ikv.getJson(true);
        if(TextUtils.isEmpty(jsonStr)){
            encryptedJson = new JSONObject();
            return encryptedJson;
        }
        jsonStr = encrypt.decrypt(getKey(),jsonStr);
        try {
            encryptedJson = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
            encryptedJson = new JSONObject();
        }

        return encryptedJson;
    }

    public static void save(String key,Object val){
        try {
            get().put(key,val);
            ikv.saveJson(json.toString(),false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveWithEncrypt(String key,Object val){
        try {
            getEncrypted().put(key,val);
            String json = encrypt.encrypt(getKey(),encryptedJson.toString());
            ikv.saveJson(json,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getKey() {
        return new UUID("SpKvImpl".hashCode(),"IKV".hashCode()).toString();
    }


}
