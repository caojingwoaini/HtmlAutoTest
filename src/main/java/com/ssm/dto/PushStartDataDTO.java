package com.ssm.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.dao.TblChargingOrderMapper;
import com.ssm.entity.TblChargingOrder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Data
public class PushStartDataDTO {
    private String StartTime;
    private String StartChargeSeq;
    private int StartChargeSeqStat;
    private String IdentCode;
    private String ConnectorID;

    public static void main(String[] args){
        boolean b=false;

        PushStartDataDTO pushStartDataDTO=new PushStartDataDTO();
        pushStartDataDTO.setStartTime("2020-09-10 14:53:29");
        pushStartDataDTO.setStartChargeSeq("MA01H3BQ1202009101453117673");
        pushStartDataDTO.setStartChargeSeqStat(2);
        pushStartDataDTO.setIdentCode("123456");
        pushStartDataDTO.setConnectorID("330106001932144501");

        PushStartDataExpect pushStartDataExpect=new PushStartDataExpect();
        pushStartDataExpect.setStartTime("2020-09-10 14:53:29");
        pushStartDataExpect.setStartChargeSeq("MA01H3BQ1202009101453117673");
        pushStartDataExpect.setStartChargeSeqStat(2);
        pushStartDataExpect.setIdentCode("123456");
        pushStartDataExpect.setConnectorID("330106001932144501");

        if (pushStartDataDTO.getStartTime().equals(pushStartDataExpect.getStartTime())){
            b=true;
        }
        System.out.println(b);

    }
}
