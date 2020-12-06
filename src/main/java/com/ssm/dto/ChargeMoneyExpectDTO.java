package com.ssm.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargeMoneyExpectDTO {
    private BigDecimal totalMoney;
    private BigDecimal chargeMoney;
    private BigDecimal serviceMoney;
    private BigDecimal FrozenMoney;
    private BigDecimal RefundMoney;


}
