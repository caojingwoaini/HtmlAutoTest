package com.ssm.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PushOrderDataDTO {
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
}
