package com.kq.gate.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: kq
 * @date: 2023-08-14 17:20:54
 * @since: 1.0.0
 * @description:
 */
public class Sm2VerifySignUtilTest {

    static String clientPublicKey = "02588faedffd7eb12047e6d82c5dbf6dc91a2db5813d5155595d2220bb5768748f";
    static String clientPrivateKey = "00df1c5a1282e0076ccbcafbf34351f3bd3e2c7aa3f344b644fccc6bd8e94652b0";

    static String serverPublicKey = "023c1366702ae9aad37b4a07c86a2645f894cc0c7b05762234461c824a3bd4acca";
    static String serverPrivateKey = "0085909c5537ab0b9ee04548540364c7e64720c9e1d7cc4d59917fad697b273ee7";

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
