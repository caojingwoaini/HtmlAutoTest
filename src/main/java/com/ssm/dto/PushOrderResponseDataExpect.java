package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import lombok.Data;

@Data
public class PushOrderResponseDataExpect {
    private String StartChargeSeq;
    private int ConfirmResult;
    private String ConnectorID;

    public static void valueof(PushOrderResponseDataExpect pushOrderResponseDataExpect, TblChargingOrder tblChargingOrder){
        pushOrderResponseDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushOrderResponseDataExpect.setConfirmResult(0);
        pushOrderResponseDataExpect.setConnectorID(tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());
    }
}
