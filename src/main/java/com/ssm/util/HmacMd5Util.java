package com.ssm.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HmacMd5Util {
    /**
     * @param str 待处理的字节数组
     * @return md5摘要信息
     * @throws NoSuchAlgorithmException
     * @descrtption 计算参数的md5信息
     */
    private static byte[] md5(byte[] str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str);
        return md.digest();
    }

    /**
     * @param key  密钥
     * @param data 待加密数据
     * @return 加密结果
     * @throws NoSuchAlgorithmException
     * @description 将待加密数据data，通过密钥key，使用hmac-md5算法进行加密，然后返回加密结果。 参照rfc2104 HMAC算法介绍实现。
     */
    private static byte[] getHmacMd5Bytes(byte[] key, byte[] data)
            throws NoSuchAlgorithmException {
        /*
         * HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
         * HmacMd5 计算公式：H(K XOR opad, H(K XOR ipad, text))
         * H代表hash算法，本类中使用MD5算法，K代表密钥，text代表要加密的数据 ipad为0x36，opad为0x5C。
         */
        int length = 64;
        byte[] ipad = new byte[length];
        byte[] opad = new byte[length];
        for (int i = 0; i < length; i++) {
            ipad[i] = 0x36;
            opad[i] = 0x5C;
        }
        byte[] actualKey = key;
        byte[] keyArr = new byte[length];
        /*
         *  如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
         */
        if (key.length > length) {
            actualKey = md5(key);
        }
        System.arraycopy(actualKey, 0, keyArr, 0, actualKey.length);
        /*
         * 如果密钥长度不足64字节，就使用0x00补齐到64字节。
         */
        if (actualKey.length < length) {
            for (int i = actualKey.length; i < keyArr.length; i++) {
                keyArr[i] = 0x00;
            }
        }
        byte[] kIpadXorResult = new byte[length];
        for (int i = 0; i < length; i++) {
            kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
        }
        byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
        System.arraycopy(kIpadXorResult, 0, firstAppendResult, 0, kIpadXorResult.length);
        System.arraycopy(data, 0, firstAppendResult, keyArr.length, data.length);
        byte[] firstHashResult = md5(firstAppendResult);
        byte[] kOpadXorResult = new byte[length];
        for (int i = 0; i < length; i++) {
            kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
        }
        byte[] secondAppendResult = new byte[kOpadXorResult.length
                + firstHashResult.length];
        System.arraycopy(kOpadXorResult, 0, secondAppendResult, 0, kOpadXorResult.length);
        System.arraycopy(firstHashResult, 0, secondAppendResult, keyArr.length, firstHashResult.length);
        return md5(secondAppendResult);
    }

    /**
     * @param key  签名密钥
     * @param data 待签名参数
     * @return
     * @description HmacMd5签名
     */
    public static String getHmacMd5Str(String key, String data) {
        String result = "";
        try {
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            byte[] dataByte = data.getBytes(StandardCharsets.UTF_8);
            byte[] hmacMd5Byte = getHmacMd5Bytes(keyByte, dataByte);
            StringBuilder md5StrBuff = new StringBuilder();
            for (byte aHmacMd5Byte : hmacMd5Byte) {
                if (Integer.toHexString(0xFF & aHmacMd5Byte).length() == 1) {
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & aHmacMd5Byte));
                } else {
                    md5StrBuff.append(Integer
                            .toHexString(0xFF & aHmacMd5Byte));
                }
            }
            result = md5StrBuff.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;

    }
}
