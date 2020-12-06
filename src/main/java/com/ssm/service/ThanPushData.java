package com.ssm.service;

import com.ssm.dto.*;

//对比所有推送数据的的结果(实际结果与预期结果进行对比)
public interface ThanPushData {
    public boolean ThanPushStartRequestData(PushStartDataDTO pushStartDataDTO, PushStartDataExpect pushStartDataExpect);

    public boolean ThanPushStartResponseData(PushStartDataResponseDTO pushStartDataResponseDTO, PushStartDataResponseExpect pushStartDataResponseExpect);

    public boolean ThanPushChargeStatusRequestData(PushChargeStatusDataDTO pushChargeStatusDataDTO,PushChargeStatusDataExpect pushChargeStatusDataExpect);

    public boolean ThanPushChargeStatusResponseData(PushChargeStatusResponseDataDTO pushChargeStatusResponseDataDTO,PushChargeStatusResponseDataExpect pushChargeStatusResponseDataExpect);

    public boolean ThanPushEquipStatusRequestData(PushEquipStatusDataDTO pushEquipStatusDataDTO,PushEquipStatusDataExpect pushEquipStatusDataExpect);

    public boolean ThanPushEquipStatusResponseData(PushEquipStatusResponseDataDTO pushEquipStatusResponseDataDTO,PushEquipStatusResponseDataExpect pushEquipStatusResponseDataExpect);

    public boolean ThanPushStopRequestData(PushStopDataDTO pushStopDataDTO,PushStopDataExpect pushStopDataExpect);

    public boolean ThanPushStopResponseData(PushStopResponseDataDTO pushStopResponseDataDTO,PushStopResponseDataExpect pushStopResponseDataExpect);

    public boolean ThanPushOrderRequestData(PushOrderDataDTO pushOrderDataDTO,PushOrderDataExpect pushOrderDataExpect);

    public boolean ThanPushOrderResponseData(PushOrderResponseDataDTO pushOrderResponseDataDTO,PushOrderResponseDataExpect pushOrderResponseDataExpect);
}
