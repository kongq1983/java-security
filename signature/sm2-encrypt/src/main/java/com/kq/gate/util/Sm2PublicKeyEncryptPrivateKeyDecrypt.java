package com.kq.gate.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

/**
 * 公钥加密，私钥解密
 *
 * @author kq
 * @date 2023-08-16 0:08
 * @since 1.0.0
 */
public class Sm2PublicKeyEncryptPrivateKeyDecrypt {


    /**
     * 公钥加密
     * @param content
     * @param publicKey
     * @return
     */
    public static String encrypt(String content,String publicKey){

        SM2 sm2 = SmUtil.sm2(null, publicKey);

        String encryptStr = sm2.encryptBcd(content, KeyType.PublicKey);

        return encryptStr;

    }

    /**
     * 私钥解密
     * @param encryptStr
     * @param privateKey
     * @return
     */
    public static String decrypt(String encryptStr,String privateKey){

        SM2 sm2 = SmUtil.sm2(privateKey, null);

        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        return decryptStr;

    }


}
