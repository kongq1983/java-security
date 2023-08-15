package com.kq.gate.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: kq
 * @date: 2023-08-14 17:20:54
 * @since: 1.0.0
 * @description:
 */
public class Sm2VerifySignUtilTest extends BaseTest{



    public static void main(String[] args) {


        JSONObject data = new JSONObject();
        data.put("id",1L);
        data.put("name","admin");
        data.put("age",18);

        data = Sm2VerifySignUtil.sign(data,clientPrivateKey);

        boolean verify = Sm2VerifySignUtil.verifySign(data,clientPublicKey);

        System.out.println("data="+data);
        System.out.println("verify="+verify);

    }


    /**
     * 私钥加密-公钥验签
     */
    public static void testPublicKeyVerify() {

        JSONObject data = getData();

        // 私钥加密
        data = Sm2VerifySignUtil.sign(data,clientPrivateKey);

        // 公钥验签
        boolean verify = Sm2VerifySignUtil.verifySign(data,clientPublicKey);

        System.out.println("data="+data);
        System.out.println("verify="+verify);

    }

    private static JSONObject getData(){

        JSONObject data = new JSONObject();
        data.put("id",1L);
        data.put("name","admin");
        data.put("age",18);

        return data;

    }

}
