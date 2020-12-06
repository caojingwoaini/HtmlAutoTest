package com.ssm.service.impl;

import com.ssm.dto.*;
import com.ssm.service.ThanPushData;
import org.springframework.stereotype.Service;

@Service
public class ThanPushDataImpl implements ThanPushData {



    @Override
    public boolean ThanPushStartRequestData(PushStartDataDTO pushStartDataDTO, PushStartDataExpect pushStartDataExpect) {
        if (!pushStartDataDTO.getStartTime().equals(pushStartDataExpect.getStartTime())){
            return false;
        }
        if (!pushStartDataDTO.getStartChargeSeq().equals(pushStartDataExpect.getStartChargeSeq())){
            return false;
        }
        if (pushStartDataDTO.getStartChargeSeqStat()!=pushStartDataExpect.getStartChargeSeqStat()){
            return false;
        }
        if (!pushStartDataDTO.getIdentCode().equals(pushStartDataExpect.getIdentCode())){
            return false;
        }
        if (!pushStartDataDTO.getConnectorID().equals(pushStartDataExpect.getConnectorID())){
            return false;
        }
        return true;
    }


    @Override
    public boolean ThanPushStartResponseData(PushStartDataResponseDTO pushStartDataResponseDTO, PushStartDataResponseExpect pushStartDataResponseExpect) {
        if (!pushStartDataResponseDTO.getStartChargeSeq().equals(pushStartDataResponseExpect.getStartChargeSeq())){
            return false;
        }
        if (pushStartDataResponseDTO.getSuccStat()!=pushStartDataResponseExpect.getSuccStat()){
            return false;
        }
        if (pushStartDataResponseDTO.getFailReason()!=pushStartDataResponseExpect.getFailReason()){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushChargeStatusRequestData(PushChargeStatusDataDTO pushChargeStatusDataDTO, PushChargeStatusDataExpect pushChargeStatusDataExpect) {
        if (!pushChargeStatusDataDTO.getTotalPower().equals(pushChargeStatusDataExpect.getTotalPower())){
            return false;
        }
        if (!pushChargeStatusDataDTO.getTotalMoney().equals(pushChargeStatusDataExpect.getTotalMoney())){
            return false;
        }
        if (!pushChargeStatusDataDTO.getStartTime().equals(pushChargeStatusDataExpect.getStartTime())){
            return false;
        }
        if (!pushChargeStatusDataDTO.getEndTime().equals(pushChargeStatusDataExpect.getEndTime())){
            return false;
        }
        if (pushChargeStatusDataDTO.getSoc()!=pushChargeStatusDataExpect.getSoc()){
            return false;
        }
        if (pushChargeStatusDataDTO.getConnectorStatus()!=pushChargeStatusDataExpect.getConnectorStatus()){
            return false;
        }
        if (!pushChargeStatusDataDTO.getCurrentA().equals(pushChargeStatusDataExpect.getCurrentA())){
            return false;
        }
        if (!pushChargeStatusDataDTO.getVoltageA().equals(pushChargeStatusDataExpect.getVoltageA())){
            return false;
        }
        if (!pushChargeStatusDataDTO.getStartChargeSeq().equals(pushChargeStatusDataExpect.getStartChargeSeq())){
            return false;
        }
        if (pushChargeStatusDataDTO.getStartChargeSeqStat()!=pushChargeStatusDataExpect.getStartChargeSeqStat()){
            return false;
        }
        if (!pushChargeStatusDataDTO.getConnectorID().equals(pushChargeStatusDataExpect.getConnectorID())){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushChargeStatusResponseData(PushChargeStatusResponseDataDTO pushChargeStatusResponseDataDTO, PushChargeStatusResponseDataExpect pushChargeStatusResponseDataExpect) {
        if (!pushChargeStatusResponseDataDTO.getStartChargeSeq().equals(pushChargeStatusResponseDataExpect.getStartChargeSeq())){
            return false;
        }
        if (pushChargeStatusResponseDataDTO.getSuccStat()!=pushChargeStatusResponseDataExpect.getSuccStat()){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushEquipStatusRequestData(PushEquipStatusDataDTO pushEquipStatusDataDTO, PushEquipStatusDataExpect pushEquipStatusDataExpect) {
        if (pushEquipStatusDataDTO.getConnectorStatusInfo().getStatus()!=pushEquipStatusDataExpect.getConnectorStatusInfo().getStatus()){
            return false;
        }
        if (pushEquipStatusDataDTO.getConnectorStatusInfo().getLockStatus()!=pushEquipStatusDataExpect.getConnectorStatusInfo().getLockStatus()){
            return false;
        }
        if (pushEquipStatusDataDTO.getConnectorStatusInfo().getParkStatus()!=pushEquipStatusDataExpect.getConnectorStatusInfo().getParkStatus()){
            return false;
        }
        if (!pushEquipStatusDataDTO.getConnectorStatusInfo().getConnectorID().equals(pushEquipStatusDataExpect.getConnectorStatusInfo().getConnectorID())){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushEquipStatusResponseData(PushEquipStatusResponseDataDTO pushEquipStatusResponseDataDTO, PushEquipStatusResponseDataExpect pushEquipStatusResponseDataExpect) {
        if (pushEquipStatusResponseDataDTO.getStatus()!=pushEquipStatusResponseDataExpect.getStatus()){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushStopRequestData(PushStopDataDTO pushStopDataDTO, PushStopDataExpect pushStopDataExpect) {
        if (!pushStopDataDTO.getStartChargeSeq().equals(pushStopDataExpect.getStartChargeSeq())){
            return false;
        }
        if (pushStopDataDTO.getSuccStat()!=pushStopDataExpect.getSuccStat()){
            return false;
        }
        if (pushStopDataDTO.getStartChargeSeqStat()!=pushStopDataExpect.getStartChargeSeqStat()){
            return false;
        }
        if (pushStopDataDTO.getFailReason()!=pushStopDataExpect.getFailReason()){
            return false;
        }
        if (!pushStopDataDTO.getConnectorID().equals(pushStopDataExpect.getConnectorID())){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushStopResponseData(PushStopResponseDataDTO pushStopResponseDataDTO, PushStopResponseDataExpect pushStopResponseDataExpect) {
        if (!pushStopResponseDataDTO.getStartChargeSeq().equals(pushStopResponseDataExpect.getStartChargeSeq())){
            return false;
        }
        if (pushStopResponseDataDTO.getSuccStat()!=pushStopResponseDataExpect.getSuccStat()){
            return false;
        }
        if (pushStopResponseDataDTO.getFailReason()!=pushStopResponseDataExpect.getFailReason()){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushOrderRequestData(PushOrderDataDTO pushOrderDataDTO, PushOrderDataExpect pushOrderDataExpect) {
        if (!pushOrderDataDTO.getStartTime().equals(pushOrderDataExpect.getStartTime())){
            return false;
        }
        if (!pushOrderDataDTO.getEndTime().equals(pushOrderDataExpect.getEndTime())){
            return false;
        }
        if (!pushOrderDataDTO.getTotalPower().equals(pushOrderDataExpect.getTotalPower())){
            return false;
        }
        if (!pushOrderDataDTO.getTotalMoney().equals(pushOrderDataExpect.getTotalMoney())){
            return false;
        }
        if (!pushOrderDataDTO.getTotalElecMoney().equals(pushOrderDataExpect.getTotalElecMoney())){
            return false;
        }
        if (!pushOrderDataDTO.getTotalSeviceMoney().equals(pushOrderDataExpect.getTotalSeviceMoney())){
            return false;
        }
        if (!pushOrderDataDTO.getStartChargeSeq().equals(pushOrderDataExpect.getStartChargeSeq())){
            return false;
        }
        if (!pushOrderDataDTO.getWorkDate().equals(pushOrderDataExpect.getWorkDate())){
            return false;
        }
        if (!pushOrderDataDTO.getVin().equals(pushOrderDataExpect.getVin())){
            return false;
        }
        if (pushOrderDataDTO.getStopReason()!=pushOrderDataExpect.getStopReason()){
            return false;
        }
        if (!pushOrderDataDTO.getConnectorID().equals(pushOrderDataExpect.getConnectorID())){
            return false;
        }
        return true;
    }

    @Override
    public boolean ThanPushOrderResponseData(PushOrderResponseDataDTO pushOrderResponseDataDTO, PushOrderResponseDataExpect pushOrderResponseDataExpect) {
        if (!pushOrderResponseDataDTO.getStartChargeSeq().equals(pushOrderResponseDataExpect.getStartChargeSeq())){
            return false;
        }
        if (!pushOrderResponseDataDTO.getConnectorID().equals(pushOrderResponseDataExpect.getConnectorID())){
            return false;
        }
        if (pushOrderResponseDataDTO.getConfirmResult()!=pushOrderResponseDataExpect.getConfirmResult()){
            return false;
        }
        return true;
    }
}
