package com.ssm.publicMethod;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

/**
 * @description: 第三方加解密工具类
 */
public class AesUtil {

    private static AesUtil instance = null;
    private static final String KEY_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    private static long lastTimeStamp = 0;
    private static int lastSeq = 1;

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
     * 中电联数据解密封装
     *
     * @param responseData 未解密的数据
     * @return JSONObject JSON信息
     */
    public static JSONObject cecDecrypt(String sKey,String ivParameter,String responseData) {
        String replace = "";
        try {
            replace = getInstance().decrypt(responseData,
                    "utf-8", sKey, ivParameter).replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.parseObject(replace);
    }


//    public static void main(String[] args){
//        String data="lkQJ+eIcvfj/9S/VBPKeLK8MyzOj5w0YeWVeFChfNnKLGTFU4Vv1ZeHK/bPVrXCFiNYnfPdcA11zf+wFm/BOMM06WmP5o/RCg/NdmaPOeRo=";
//        JSONObject data2=new AesUtil().cecDecrypt("50a61b93919c9604","7c8ac6861661d584",data);
//        System.out.println(data2);
//    }

}
