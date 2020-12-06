package com.ssm.service;

import com.ssm.dto.ChargeMoneyExpectDTO;
import com.ssm.dto.ChargeMoneyReallyDTO;

public interface AutoTest {

    public Boolean thanChargeMoney(ChargeMoneyExpectDTO chargeMoneyExpectDTO, ChargeMoneyReallyDTO chargeMoneyReallyDTO);
}
