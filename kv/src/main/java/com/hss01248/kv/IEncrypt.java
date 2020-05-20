package com.hss01248.kv;

/**
 * by hss
 * data:2020-05-19
 * desc:
 */
public interface IEncrypt {

    String encrypt(String key,String str);

    String decrypt(String key,String str);
}
