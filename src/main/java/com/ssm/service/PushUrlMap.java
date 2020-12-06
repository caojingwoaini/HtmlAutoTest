package com.ssm.service;

import com.ssm.entity.TblPartner;

import java.util.Map;

public interface PushUrlMap {
    public Map<String,String> pushUrlMapInfo(TblPartner tblPartner,Map<String,String> pushUrlMap);
}
