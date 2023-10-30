package com.kq.gate.util;

import cn.hutool.crypto.SmUtil;

/**
 * @author: kq
 * @date: 2023-08-22 15:35:19
 * @since: 1.0.0
 * @description:
 */
public class PasswordTest {

    public static void main(String[] args) {
        String userName = "admin";
        String password = "123456";
        String encryptPwd = encryptPwd(userName, password);
        System.out.println(encryptPwd);
    }

    public static String encryptPwd(String accountNumber, String password) {
        return SmUtil.sm3(accountNumber + password);
    }

}
