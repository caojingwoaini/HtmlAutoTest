package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import lombok.Data;

@Data
public class PushStopResponseDataExpect {
    private String StartChargeSeq;
    private int SuccStat;
    private int FailReason;

    public static void valueof(PushStopResponseDataExpect pushStopResponseDataExpect, TblChargingOrder tblChargingOrder){
        pushStopResponseDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushStopResponseDataExpect.setSuccStat(0);
        pushStopResponseDataExpect.setFailReason(0);
    }
}
