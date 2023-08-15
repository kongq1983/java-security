package com.kq.gate.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.asymmetric.SM2;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 私钥生成sign，公钥验签
 * 本类只验证签名验证
 * @author: kq
 * @date: 2023-08-14 16:59:15
 * @since: 1.0.0
 * @description:
 */
public class Sm2VerifySignUtil {


    /**
     *
     * 服务端:(sm2私钥签名)  ->  客户端:(sm2公钥验签)
     * 服务器响应签名
     * @param data
     * @param privateKey
     * @return
     */
    public static JSONObject sign(JSONObject data, String privateKey) {
        // 打印参数
        Map<String, Object> clientMap = JSONObject.parseObject(data.toJSONString(), new TypeReference<Map<String, Object>>(){});
        printMap(clientMap);
        // 参数排序转字符串
        String clientStr = buildSignStr(clientMap);
        // SM2加签
        SM2 csm2 = new SM2(privateKey, null);
        csm2.usePlainEncoding();
        String sign = HexUtil.encodeHexStr(csm2.sign(clientStr.getBytes(StandardCharsets.UTF_8)));
        clientMap.put("sign", sign);
        JSONObject jsonObject = new JSONObject(clientMap);
        return jsonObject;
    }


    /**
     * 验证签名
     * @param data
     * @param publicKey
     * @return
     */
    public static boolean verifySign(JSONObject data,String publicKey) {
        Map<String, Object> serviceMap = JSONObject.parseObject(data.toJSONString(), new TypeReference<Map<String, Object>>(){});
        // 获取签名
        String serviceSign = (String) serviceMap.get("sign");
        serviceMap.remove("sign");
        // 参数排序转字符串
        String serviceStr = buildSignStr(serviceMap);

        // 验签
        SM2 ssm2 = new SM2(null, publicKey);
        ssm2.usePlainEncoding();
        System.out.println("服务端验证字符串:{}"+serviceStr);
        System.out.println("服务端验证公钥:{}"+publicKey);

        boolean verify = ssm2.verify(serviceStr.getBytes(StandardCharsets.UTF_8), HexUtil.decodeHex(serviceSign));

        return verify;
    }


    /**
     * 字典排序拼接
     *
     * @param params
     * @return
     */
    private static String buildSignStr(Map<String, Object> params) {
        if (null == params || params.size() == 0) {
            return null;
        }
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<>(params);
        Set<Map.Entry<String, Object>> entrys = sortParams.entrySet();
        // 遍历排序的字典,并拼接"key=value"格式，值为null的字段不拼接
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, Object> entry : entrys) {
            if (null != entry.getValue()) {
                baseString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        baseString.deleteCharAt(baseString.length() - 1);

        System.out.println("buildSignStr="+baseString);

        return baseString.toString();
    }


    private static void printMap(Map<String, Object> params) {
        String  paramStr="";
        for(String key : params.keySet()){
            paramStr+=key+":"+params.get(key)+" ";
        }
        System.out.println("服务端接受数据:"+paramStr);
    }

}
