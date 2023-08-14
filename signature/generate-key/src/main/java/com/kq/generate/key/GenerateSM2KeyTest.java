package com.kq.generate.key;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;

/**
 * @author: kq
 * @date: 2023-07-18 16:24:39
 * @since: 1.0.0
 * @description:
 */
public class GenerateSM2KeyTest {

    public static void main(String[] args) {
//        generateSm2();
//        generateSm2One();
//        System.out.println("");
//        generateSm2Two();
//        System.out.println();
        // 使用这个
        generatorKey();
    }


    private static void generateSm2(){

        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        SM2 sm2 = SmUtil.sm2(privateKey, publicKey);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println("encryptStr="+encryptStr);
        System.out.println("decryptStr="+decryptStr);

    }

    private static void generateSm2One(){

        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        String privateKeyStr = HexUtil.encodeHexStr(privateKey);
        String publicKeyStr = HexUtil.encodeHexStr(publicKey);

        SM2 sm2 = SmUtil.sm2(privateKeyStr, publicKeyStr);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println("privateKeyStr="+privateKeyStr);
        System.out.println("publicKeyStr="+publicKeyStr);
        System.out.println("encryptStr="+encryptStr);
        System.out.println("decryptStr="+decryptStr);

    }

    private static void generateSm2Two(){

        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();

        String privateKeyStr = ((BCECPrivateKey) pair.getPrivate()).getD().toString(16).toUpperCase();
        System.out.println("private_key:----" + privateKeyStr);
        String publicKeyStr = Hex.toHexString(((BCECPublicKey) pair.getPublic()).getQ().getEncoded(false)).toUpperCase();
        System.out.println("pub_key---------" + publicKeyStr);

        SM2 sm2 = SmUtil.sm2(privateKeyStr, publicKeyStr);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println("privateKeyStr="+privateKeyStr);
        System.out.println("publicKeyStr="+publicKeyStr);
        System.out.println("encryptStr="+encryptStr);
        System.out.println("decryptStr="+decryptStr);

    }


    /**
     * 使用这个
     */
    public static void generatorKey(){

        String text = "我是一段测试aaaa";
        // 客户端创建公私钥对
        KeyPair cPair = SecureUtil.generateKeyPair("SM2");
        String  privateKeyStr = HexUtil.encodeHexStr(BCUtil.encodeECPrivateKey(cPair.getPrivate()));
        String  publicKeyStr = HexUtil.encodeHexStr(BCUtil.encodeECPublicKey(cPair.getPublic()));
        System.out.println("privateKeyStr="+privateKeyStr);
        System.out.println("publicKeyStr="+publicKeyStr);

        SM2 sm2 = SmUtil.sm2(privateKeyStr, publicKeyStr);
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));

        System.out.println("encryptStr="+encryptStr);
        System.out.println("decryptStr="+decryptStr);
    }

    // privateKeyStr= 789405dc3917f1018a52388cbcea091ecde20c57e3aee7be234c9dac2461cbbb
    // publicKeyStr= 0359b02930481c3385ce9c21ba0a847dcd8f0c8f653fd87153dd000a6200c40089


//    privateKeyStr=00df1c5a1282e0076ccbcafbf34351f3bd3e2c7aa3f344b644fccc6bd8e94652b0
//    publicKeyStr=02588faedffd7eb12047e6d82c5dbf6dc91a2db5813d5155595d2220bb5768748f


}
