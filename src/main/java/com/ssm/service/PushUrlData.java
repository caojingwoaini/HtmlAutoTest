package com.ssm.service;

import com.ssm.entity.TblChargingOrder;

import java.util.ArrayList;
import java.util.Map;

public interface PushUrlData {
    public Map<String,String> PushUrlDataInfo(Map<String,String> pushUrlMap, TblChargingOrder tblChargingOrder);
}
