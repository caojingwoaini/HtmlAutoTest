package com.ssm.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TblChargingOrder {
    private String pkChargingorder; // 主键
    private String chorCode; // 充电订单编号
    private String chorAppointmencode; // 预约流水号
    private String chorPilenumber; // 桩体编号
    private String chorUserid; // 用户ID(企业ID)
    private int chorType; // 用户类型
    private BigDecimal chorMoeny; // (收益)金额
    private BigDecimal chorQuantityelectricity; // 电量
    private String chorTimequantum; // 时间段
    private int chorMuzzle; //枪口编号
    private String chorChargingstatus; //订单状态
    private String chorTranstype; //交易类型
    private Date chorCreatedate; //创建时间
    private Date chorUpdatedate; // 修改时间
    private String chorUsername; // 用户姓名(企业姓名)
    private String chorTransactionnumber; // 交易流水号
    private int chorOrdertype; // 1支付宝 2银联
    private BigDecimal chorChargemoney;// 充电金额
    private BigDecimal chorServicemoney;//充电服务费金额
    private BigDecimal chOr_tipPower;//尖时段用电度数
    private BigDecimal chOr_peakPower;//峰时段用电度数
    private BigDecimal chOr_usualPower;//平时段用电度数
    private BigDecimal chOr_valleyPower;//谷时段用电度数
    private String chOrOrgNo;//第三方用户标识
    private String beginChargetime;//充电开始时间
    private String endChargetime;//充电结束时间
    private int endSoc;

    private int chOrUserOrigin;
    private int pkUserCard;
    private BigDecimal couponMoney;//优惠券抵扣金额
    private String couponCondition;//优惠券使用条件
    private String limitation;//电桩限制

    private BigDecimal countMoney; // 支付款总金额，与上面chorMoeny对应，精度更准

    private Integer payMode;

    private String chorParterUserLogo;
    private String chorParterExtradata;
    private String cv_vin_code;
}
