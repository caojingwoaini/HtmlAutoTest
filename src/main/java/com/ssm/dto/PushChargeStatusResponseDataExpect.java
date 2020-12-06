package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import lombok.Data;

@Data
public class PushChargeStatusResponseDataExpect {
    private String StartChargeSeq;
    private int SuccStat;

    public static void valueof(PushChargeStatusResponseDataExpect pushChargeStatusResponseDataExpect, TblChargingOrder tblChargingOrder){
        pushChargeStatusResponseDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushChargeStatusResponseDataExpect.setSuccStat(0);
    }
}
