package com.ssm.dao;


import com.ssm.entity.TblPartner;

public interface TblPartnerMapper {

    //查询第三方公司信息
    public TblPartner tblPartnerInfo(String cpyId);

}
