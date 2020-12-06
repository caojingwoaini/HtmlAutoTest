package com.ssm.dao;

import com.ssm.entity.TblPurchasehistory;

import java.util.List;

public interface TblPurchasehistoryMapper {

    public List<TblPurchasehistory> tblPurchasehistoryInfo(String transactionNumber);

}
