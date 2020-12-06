package com.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;
import com.ssm.entity.TblPartner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class AesUtil {
    /**
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static AesUtil instance = null;
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    private static long lastTimeStamp = 0;
    private static int lastSeq = 1;

    private AesUtil() {

    }


    /**
     * 构造方法
     *
     * @return instance
     */
    public static AesUtil getInstance() {
        if (instance == null) {
            instance = new AesUtil();
        }
        return instance;
    }

    /**
     * Aes加密方法
     *
     * @param sSrc           待加密的参数
     * @param encodingFormat 加密格式
     * @param sKey           加密密钥
     * @param ivParameter    加密向量
     * @return String 加密后的字符
     */
    public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
        } catch (Exception e) {
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
        /*使用CBC模式，需要一个向量iv，可增加加密算法的强度*/
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        byte[] encrypted;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        } catch (Exception e) {
            return null;
        }
        /*此处使用BASE64做转码*/
        return new BASE64Encoder().encode(encrypted);
    }


    /**
     * Aes解密方法
     *
     * @param sSrc           待解密参数
     * @param encodingFormat 参数格式
     * @param sKey           加密密钥
     * @param ivParameter    加密向量
     * @return String 加密后的字符
     */
    public String decrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) {
        try {
            byte[] raw = sKey.getBytes(StandardCharsets.US_ASCII);
            SecretKeySpec keySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            return getString(sSrc, encodingFormat, ivParameter, keySpec, cipher);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getString(String sSrc, String encodingFormat, String ivParameter, SecretKeySpec keySpec, Cipher cipher) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, encodingFormat);
    }


    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    /**
     * 格式化时间
     */
    public static Long getNowTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String time = dateTimeFormatter.format(localDateTime);
        return Long.parseLong(time);
    }

    /**
     * 4位自增序列，同一秒内自增，如0001
     */
    public static String getSeq(long thisTimeStamp) {
        // 第一次时
        if (lastTimeStamp == thisTimeStamp) {
            lastSeq++;
            return String.format("%04d", lastSeq);
        }
        lastSeq = 1;
        lastTimeStamp = thisTimeStamp;
        return String.format("%04d", lastSeq);
    }


//    /**
//     * 中电联数据加密封装
//     *
//     * @param tblPartner 运营商信息
//     * @param data       数据对象
//     * @return String
//     */
//    public String cecEncrypt(TblPartner tblPartner, Object data) {
//        String date = getInstance().encrypt(
//                new GsonBuilder().disableHtmlEscaping().create().toJson(data), "utf-8", tblPartner.getAesSecret(), tblPartner.getAesIv()).replace("\n", "").replace("\r", "");
//        String sigKey = 0 + "Request Success" + date;
//        return CecControllerResult.resultSuccess(0,"Request Success", date, sigKey, tblPartner.getSigSecret());
//    }


    /**
     * 中电联数据解密封装
     *
     * @param tblPartner   运营商信息
     * @return JSONObject JSON信息
     */
    public JSONObject cecDecrypt(TblPartner tblPartner, String data) {
        String replace = "";
        try {
            replace = getInstance().decrypt(data,
                    "utf-8", tblPartner.getThird_sKey(), tblPartner.getThird_ivParameter()).replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.parseObject(replace);
    }


    public static void main(String[] args) {


    }
}
