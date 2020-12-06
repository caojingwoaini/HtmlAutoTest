package com.ssm.dao;


import com.ssm.entity.TblChargingOrder;

public interface TblChargingOrderMapper {
    //查询交易流水号是否存在于数据库中
    public int transactionNumberisHaveDb(String transactionNumber);

    /*
    查询充电订单的相关信息(充电订单编号/桩体编号/用户ID/充电消费金额/尖时段用电度数/
    峰时段用电度数/平时段用电度数/谷时段用电度数/总电量/枪口编号/订单状态/充电金额/
    服务费金额/充电开始时间/充电结束时间/公司标识/优惠券抵扣金额/第三方合作方用户标识/
    第三方流水号或者会话ID/vin码)
     */
    public TblChargingOrder getTblChargingOrder(String transactionNumber);
}
