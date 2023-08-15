package com.kq.gate.util;

/**
 * Sm2PrivateKeyEncryptPublicKeyDecryptTest
 *
 * @author kq
 * @date 2023-08-16 0:24
 * @since 1.0.0
 */
public class Sm2PrivateKeyEncryptPublicKeyDecryptTest extends BaseTest{

    public static void main(String[] args) {

        String content = "welcome to you! 欢迎!";

        // 私钥加密  这里会报错(Encrypt is only support by public key)
        String encryptStr = Sm2PrivateKeyEncryptPublicKeyDecrypt.encrypt(content,clientPrivateKey);
        // 公钥解密
        String decryptStr = Sm2PrivateKeyEncryptPublicKeyDecrypt.decrypt(encryptStr,clientPublicKey);

        System.out.println("encryptStr="+encryptStr);
        System.out.println("decryptStr="+decryptStr);
    }

}
