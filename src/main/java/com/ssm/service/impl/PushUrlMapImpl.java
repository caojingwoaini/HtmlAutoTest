package com.ssm.service.impl;

import com.google.common.base.Strings;
import com.ssm.entity.TblPartner;
import com.ssm.service.PushUrlMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PushUrlMapImpl implements PushUrlMap {

    public Map<String, String> pushUrlMapInfo(TblPartner tblPartner,Map<String,String> pushUrlMap) {
        if (!Strings.isNullOrEmpty(tblPartner.getPushStartUrl())){
            pushUrlMap.put("pushStartUrl",tblPartner.getPushStartUrl());
        }
        //实时数据
        if (!Strings.isNullOrEmpty(tblPartner.getPushChargeStatusUrl())){
            pushUrlMap.put("pushChargeStatusUrl",tblPartner.getPushChargeStatusUrl());
        }
        //设备状态
        if (!Strings.isNullOrEmpty(tblPartner.getPushEquipStatusUrl())){
            pushUrlMap.put("pushEquipStatusUrl",tblPartner.getPushEquipStatusUrl());
        }
        if (!Strings.isNullOrEmpty(tblPartner.getPushStopUrl())){
            pushUrlMap.put("pushStopUrl",tblPartner.getPushStopUrl());
        }
        if (!Strings.isNullOrEmpty(tblPartner.getPushOrderUrl())){
            pushUrlMap.put("pushOrderUrl",tblPartner.getPushOrderUrl());
        }
        return pushUrlMap;
    }
}
