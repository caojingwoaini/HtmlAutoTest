package com.ssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.ssm.entity.TblPartner;
import com.ssm.publicMethod.AesUtil;
import com.ssm.service.Jiemi;
import org.springframework.stereotype.Service;

@Service
public class JiemiImpl implements Jiemi {

    @Override
    public String decrypt2(String pushData, TblPartner tblPartner) {
        String responseData= JSON.parseObject(pushData.split("\\|")[3].replaceAll("jsonObject:","").replaceAll("\r\n","")).getString("Data");
        String responseData2;
        if (tblPartner.getThird_sKey()!=null){
            responseData2= AesUtil.getInstance().decrypt(responseData,"utf-8",tblPartner.getThird_sKey(),tblPartner.getThird_ivParameter());
        }else {
            responseData2= AesUtil.getInstance().decrypt(responseData,"utf-8",tblPartner.getWm_sKey(),tblPartner.getWm_ivParameter());
        }
        return responseData2;
    }
}
