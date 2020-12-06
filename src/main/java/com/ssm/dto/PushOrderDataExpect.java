package com.ssm.dto;

import com.ssm.entity.TblChargingOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PushOrderDataExpect {
    private String StartTime;
    private String EndTime;
    private BigDecimal TotalPower;
    private BigDecimal TotalMoney;
    private BigDecimal TotalElecMoney;
    private BigDecimal TotalSeviceMoney;
    private String StartChargeSeq;
    private String WorkDate;
    private String Vin;
    private int StopReason;
    private String ConnectorID;

    public static void valueof(PushOrderDataExpect pushOrderDataExpect, TblChargingOrder tblChargingOrder){
        pushOrderDataExpect.setStartTime(tblChargingOrder.getBeginChargetime());
        pushOrderDataExpect.setEndTime(tblChargingOrder.getEndChargetime());
        pushOrderDataExpect.setTotalPower(tblChargingOrder.getChorQuantityelectricity().setScale(2, BigDecimal.ROUND_HALF_UP));
        pushOrderDataExpect.setTotalMoney(tblChargingOrder.getChorMoeny().setScale(2,BigDecimal.ROUND_HALF_UP));
        pushOrderDataExpect.setTotalElecMoney(tblChargingOrder.getChorChargemoney().setScale(2,BigDecimal.ROUND_HALF_UP));
        pushOrderDataExpect.setTotalSeviceMoney(tblChargingOrder.getChorServicemoney().setScale(2,BigDecimal.ROUND_HALF_UP));
        pushOrderDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushOrderDataExpect.setWorkDate(tblChargingOrder.getEndChargetime().substring(0,10));
        pushOrderDataExpect.setVin(tblChargingOrder.getCv_vin_code());
        pushOrderDataExpect.setStopReason(1);
        pushOrderDataExpect.setConnectorID(tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());
    }
}
