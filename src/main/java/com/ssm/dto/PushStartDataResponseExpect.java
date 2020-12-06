package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import lombok.Data;

@Data
public class PushStartDataResponseExpect {
    private String StartChargeSeq;
    private int SuccStat;
    private int FailReason;

    public static void valueof(PushStartDataResponseExpect pushStartDataResponseExpect,TblChargingOrder tblChargingOrder){
        pushStartDataResponseExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushStartDataResponseExpect.setSuccStat(0);
        pushStartDataResponseExpect.setFailReason(0);
    }
}
