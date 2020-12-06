package com.ssm.service.impl;

import com.google.common.base.Strings;
import com.ssm.dao.TblChargingOrderMapper;
import com.ssm.service.IsHave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IsHaveImpl implements IsHave {

    @Autowired
    private TblChargingOrderMapper tblChargingOrderMapper;



    public ArrayList<String>  SecretAndThird_secret(String Secret,String third_secret){
        ArrayList<String> list=new ArrayList<String>();
        if (Strings.isNullOrEmpty(Secret)){
            return list;
        }
        //双方加密密钥不一致
        if (!Strings.isNullOrEmpty(third_secret)){
            list.add(Secret);
            list.add(third_secret);
        }else {
        //双方加密密钥一致
            list.add(Secret);
            list.add(Secret);
        }
        return list;
    }
}
