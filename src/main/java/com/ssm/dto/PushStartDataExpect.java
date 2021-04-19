package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import com.ssm.entity.TblPartner;
import lombok.Data;

@Data
public class PushStartDataExpect {
    private String StartTime;
    private String StartChargeSeq;
    private int StartChargeSeqStat;
    private String IdentCode;
    private String ConnectorID;

    public static void valueof(PushStartDataExpect pushStartDataExpect, TblChargingOrder tblChargingOrder,String pushData){
        pushStartDataExpect.setStartTime(pushData.split(",")[0]);
        pushStartDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushStartDataExpect.setStartChargeSeqStat(2);
        pushStartDataExpect.setIdentCode("123123");
        pushStartDataExpect.setConnectorID(tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());
    }
}
