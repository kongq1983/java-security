package com.kq.gate.util;

/**
 * Sm2PublicKeyEncryptPrivateKeyDecryptTest
 *
 * @author kq
 * @date 2023-08-16 0:14
 * @since 1.0.0
 */
public class Sm2PublicKeyEncryptPrivateKeyDecryptTest extends BaseTest{

    public static void main(String[] args) {

        String content = "welcome to you! 欢迎!";

        // 公钥加密  (Encrypt is only support by public key)
        String encryptStr = Sm2PublicKeyEncryptPrivateKeyDecrypt.encrypt(content,clientPublicKey);

        String decryptStr = Sm2PublicKeyEncryptPrivateKeyDecrypt.decrypt(encryptStr,clientPrivateKey);

        System.out.println("encryptStr="+encryptStr);
        System.out.println("decryptStr="+decryptStr);
    }

}
