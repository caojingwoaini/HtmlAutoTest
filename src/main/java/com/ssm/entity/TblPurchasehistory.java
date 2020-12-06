package com.ssm.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TblPurchasehistory {
    private int pk_PurchaseHistory;
    private int puHi_Type;
    private String puHi_PurchaseHistoryTime;
    private BigDecimal puHi_Monetary;
    private int puHi_UserId;
    private String puHi_ElectricPileCode;
    private String puHi_TransactionNumber;
    private BigDecimal account_id;
}
