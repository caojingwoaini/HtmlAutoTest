package com.ssm.service.impl;

import com.ssm.dto.ChargeMoneyExpectDTO;
import com.ssm.dto.ChargeMoneyReallyDTO;
import com.ssm.service.AutoTest;
import org.springframework.stereotype.Service;

@Service
public class AutoTestImpl implements AutoTest {

    public Boolean thanChargeMoney(ChargeMoneyExpectDTO chargeMoneyExpectDTO, ChargeMoneyReallyDTO chargeMoneyReallyDTO) {
        if (!chargeMoneyExpectDTO.getChargeMoney().equals(chargeMoneyReallyDTO.getChargeMoney())){
            return false;
        }
        if (!chargeMoneyExpectDTO.getServiceMoney().equals(chargeMoneyReallyDTO.getServiceMoney())){
            return false;
        }
        if (!chargeMoneyExpectDTO.getTotalMoney().equals(chargeMoneyReallyDTO.getTotalMoney())){
            return false;
        }
        if (!chargeMoneyExpectDTO.getFrozenMoney().equals(chargeMoneyReallyDTO.getFrozenMoney())){
            return false;
        }
        if (!chargeMoneyExpectDTO.getRefundMoney().equals(chargeMoneyReallyDTO.getRefundMoney())){
            return false;
        }
        return true;
    }
}
