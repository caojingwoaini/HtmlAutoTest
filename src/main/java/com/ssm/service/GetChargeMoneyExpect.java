package com.ssm.service;

import com.ssm.dto.ChargeMoneyExpectDTO;
import com.ssm.dto.ChargeMoneyReallyDTO;
import com.ssm.entity.TblChargingOrder;
import com.ssm.entity.TblChargingrecord;
import com.ssm.entity.TblPurchasehistory;

import java.util.List;

public interface GetChargeMoneyExpect {

    public ChargeMoneyExpectDTO chargeMoneyExpectInfo(TblChargingOrder tblChargingOrder, TblChargingrecord tblChargingrecord,ChargeMoneyExpectDTO chargeMoneyExpectDTO,int personalityFlag);

    public ChargeMoneyReallyDTO chargeMoneyReallyInfo(TblChargingOrder tblChargingOrder, TblChargingrecord tblChargingrecord, List<TblPurchasehistory> tblPurchasehistory,ChargeMoneyReallyDTO chargeMoneyReallyDTO);
}
