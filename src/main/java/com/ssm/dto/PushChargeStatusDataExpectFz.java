package com.ssm.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class PushChargeStatusDataExpectFz {
    private String workStatus;
    private String outCurrent;
    private String chargeAmt;
    private String totalTime;
    private String soc;
    private String chargeMeterNum;
    private String rateInfo;
    private String chargeRemainTime;
    private String fronzeAmt;
    private String deviceStatus;
    private String outVol;
    private String servicePrice;
    private String warns;
    private String startTime;
    private String elecAmt;
    private String totalPower;



    public static void main(String[] args){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str=format.format(Long.valueOf("1599793194000"));
        System.out.println(str);
    }
}
