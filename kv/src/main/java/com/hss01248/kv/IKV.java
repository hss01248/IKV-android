package com.hss01248.kv;

import org.json.JSONObject;

/**
 * by hss
 * data:2020-05-19
 * desc:
 * 此处不进行加解密操作,只作为存储
 */
public interface IKV {


    void saveJson(String json,boolean encrypt);

    String getJson(boolean encrypt);
}
