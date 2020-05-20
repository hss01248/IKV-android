package com.hss01248.kv;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * by hss
 * data:2020-05-19
 * desc:
 */
public class SpKvImpl implements IKV {

    @Override
    public void saveJson(String json, boolean encrypt) {
        KvUtil.app.getSharedPreferences("SpKvImpl", Context.MODE_PRIVATE).edit().putString(getKey(encrypt),json).apply();
    }

    public String getKey(boolean encrypt) {
        return "SpKvImpl"+encrypt;
    }

    @Override
    public String getJson(boolean encrypt) {
       return KvUtil.app.getSharedPreferences("SpKvImpl", Context.MODE_PRIVATE).getString("SpKvImpl"+encrypt, "");

    }


}
