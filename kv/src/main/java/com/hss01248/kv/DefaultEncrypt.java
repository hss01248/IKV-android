package com.hss01248.kv;

import com.gcssloop.encrypt.symmetric.DESUtil;

import javax.crypto.Cipher;

/**
 * by hss
 * data:2020-05-19
 * desc:
 */
public class DefaultEncrypt implements IEncrypt {
    @Override
    public String encrypt(String key, String str) {
        return DESUtil.des(str, key, Cipher.ENCRYPT_MODE);
    }

    @Override
    public String decrypt(String key, String str) {
        return DESUtil.des(str, key, Cipher.DECRYPT_MODE);
    }
}
