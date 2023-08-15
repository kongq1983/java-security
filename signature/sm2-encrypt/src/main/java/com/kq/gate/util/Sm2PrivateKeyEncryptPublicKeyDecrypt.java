package com.kq.gate.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

/**
 * 私钥加密，公钥解密
 * 这个会报错的 Encrypt is only support by public key
 * @author kq
 * @date 2023-08-16 0:08
 * @since 1.0.0
 */
public class Sm2PrivateKeyEncryptPublicKeyDecrypt {


    /**
     * 私钥加密
     * @param content
     * @param privateKey
     * @return
     */
    public static String encrypt(String content,String privateKey){

        SM2 sm2 = SmUtil.sm2(privateKey, null);

        String encryptStr = sm2.encryptBcd(content, KeyType.PrivateKey);

        return encryptStr;

    }

    /**
     * 公钥解密
     * @param encryptStr
     * @param publicKey
     * @return
     */
    public static String decrypt(String encryptStr,String publicKey){

        SM2 sm2 = SmUtil.sm2(null, publicKey);

        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PublicKey));

        return decryptStr;

    }


}
