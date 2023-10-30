package com.kq.rsa;

import com.kq.rsa.encrypt.util.CryptoUtils;

/**
 * @author: kq
 * @date: 2023-10-30 16:58:53
 * @since: 1.0.0
 * @description:
 */
public class RsaTest {

    public static void main(String[] args) throws Exception{

        CryptoUtils.AsymmetricKeyPair keyPair = CryptoUtils.generateAsymmetricKeyPair(CryptoUtils.Algorithm.Encryption.RSA_ECB_PKCS1);
        String privateKey = keyPair.getPrivateKey();
        String publicKey = keyPair.getPublicKey();
        System.out.println("生成的私钥为：\n" + privateKey);
        System.out.println("生成的公钥为：\n" + publicKey);

        String content = "test";

        String signature = CryptoUtils.signBySHA256WithRSA(privateKey, content);
        System.out.println("生成的签名为：\n" + signature);


        boolean verify = CryptoUtils.verifyBySHA256WithRSA(publicKey,content,signature);

        System.out.println("verify="+verify);

    }

}
