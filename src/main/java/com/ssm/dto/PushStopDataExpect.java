package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import lombok.Data;

@Data
public class PushStopDataExpect {
    private String StartChargeSeq;
    private int SuccStat;
    private int StartChargeSeqStat;
    private int FailReason;
    private String ConnectorID;

    public static void valueof(PushStopDataExpect pushStopDataExpect, TblChargingOrder tblChargingOrder){
        pushStopDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushStopDataExpect.setSuccStat(0);
        pushStopDataExpect.setStartChargeSeqStat(4);
        pushStopDataExpect.setFailReason(0);
        pushStopDataExpect.setConnectorID(tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());
    }
}
