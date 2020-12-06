package com.ssm.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PushChargeStatusDataDTO {
    private BigDecimal TotalPower;
    private BigDecimal TotalMoney;
    private BigDecimal ElecMoney;
    private BigDecimal SeviceMoney;
    private String StartTime;
    private String EndTime;
    private int Soc;
    private int ConnectorStatus;
    private BigDecimal CurrentA;
    private BigDecimal VoltageA;
    private String StartChargeSeq;
    private int StartChargeSeqStat;
    private String ConnectorID;
}
