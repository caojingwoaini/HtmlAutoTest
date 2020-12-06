package com.ssm.entity;

import com.google.common.base.Strings;
import lombok.Data;

@Data
public class TblPartner {
    private int pk_partner;
    private String partnerName;
    private String partnerKey;
    private String partnerToken;
    private String partnerClientKey;
    private int valid;
    private int paymod;
    private String tokenUrl;
    private String tokenSecret;
    private String secret;
    private String pushStartUrl;
    private String pushStopUrl;
    private String pushOrderUrl;
    private String pushEquipStatusUrl;
    private String pushOrderCheckUrl;
    private String pushChargeStatusUrl;
    private int cpy_id;
    private String compatible;
    private String third_secret;
    private String serviceCode;
    private String equipmentOwnerId;

    //以下字段不作为数据库对应关系
    //万马加密密钥
    private String wm_sKey;

    //万马加密向量
    private String wm_ivParameter;

    //万马签名密钥
    private String wm_SigSecret;

    //第三方加密密钥
    private String third_sKey;

    //第三方加密向量
    private String third_ivParameter;

    //第三方签名密钥
    private String third_SigSecret;

    //封装加解密参数
    public static void checkTblPartner(TblPartner tblPartner){
        if (!Strings.isNullOrEmpty(tblPartner.getSecret())){
            String wm_sKey=tblPartner.getSecret().split("\\|")[0];
            String wm_ivParameter=tblPartner.getSecret().split("\\|")[1];
            String wm_SigSecret=tblPartner.getSecret().split("\\|")[2];
            tblPartner.setWm_sKey(wm_sKey);
            tblPartner.setWm_ivParameter(wm_ivParameter);
            tblPartner.setWm_SigSecret(wm_SigSecret);
        }
        if (!Strings.isNullOrEmpty(tblPartner.getThird_secret())){
            String third_sKey=tblPartner.getThird_secret().split("\\|")[0];
            String third_ivParameter=tblPartner.getThird_secret().split("\\|")[1];
            String third_SigSecret=tblPartner.getThird_secret().split("\\|")[2];
            tblPartner.setThird_sKey(third_sKey);
            tblPartner.setThird_ivParameter(third_ivParameter);
            tblPartner.setThird_SigSecret(third_SigSecret);
        }
    }
}
