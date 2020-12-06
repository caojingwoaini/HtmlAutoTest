package com.ssm.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TblElectricpile {
    /**
     * 主键
     **/
    private Integer pkElectricPile;

    /**
     * 电桩名称
     **/
    private String elpiElectricpilename;

    /**
     * 桩体编号
     **/
    private String elpiElectricpilecode;

    private String elpiElectricpileaddress; // 地址
    private BigDecimal elpiLongitude; // 电桩地址经度
    private BigDecimal elpiLatitude; // 电桩地址维度
    private Integer elpiPowernumber; // 电桩枪口数量
    private String elpiAreacode; // 电桩所属区域代码(根据省、市、区表关联)
    private Integer elpiState; // 电桩状态（0-草稿 5-提交审核 3-已驳回 10-离线15-上线）
    private String elpiRejectionreason; // 审核驳回原因
    private Integer elpiType; // 电桩类型，配置参数内容的ID （落地式，壁挂式，拉杆式，便携式）
    private Integer elpiPoweruser; // 电桩用途，配置参数内容的ID（电动车，电动自行车，多功能（电动车、电动自行车、手机USB））
    private Integer elpiChargingmode; // 电桩充电方式，配置参数内容的ID （直流充电桩，交流充电桩）
    private Integer elpiPowersize; // 电桩额定功率，配置参数内容的ID（3.5kw，7kw，20kw，50kw，75kw）
    private Integer elpiPowerinterface; // 电桩接口方式，配置参数内容的ID（国标、欧标、美标）7国标，19美标，20欧标
    private Integer elpiMaker; // 电桩制造商，配置参数内容的ID（万马新能源，南京循道，北京三优，上海普天）
    private String elpiImage; // 电桩图片
    private String elpiDetailimage; // 电桩详情图片
    private BigDecimal elpiOutputvoltage; // 电桩额定输出电压
    private BigDecimal elpiInputvoltage; // 电桩额定输入电压
    private BigDecimal elpiOutputcurrent; // 电桩额定输出电流
    private BigDecimal elpiInputcurrent; // 电桩额定输入电流
    private Integer elpiUsertype; // 电桩所属用户类型
    private String elpiUserid; // 电桩所属用户ID
    private String elpiCreatedate; // 创建时间
    private String elpiUpdatedate; // 修改时间
    private String elpiRemark; // 备注
    private String elpiCarid; // 电桩适用车型，根据电动车品牌类型详情表关联，id用逗号隔开
    private Integer elpiBinding; // 电桩是否绑定电站（0-未绑定 1-已绑定）
    private Integer elpiIsappoint; // 电桩是否支持预约（0不支持，1支持）
    private Integer elpiPaystyle; // 电桩充电支付方式，配置参数内容的ID（刷卡方式，手机方式，人工方式）
    private BigDecimal elpiMaxelectricity; //最大电流
    private String elpiPowerusername; // 充电模式用途名称
    private String elpiChargingmodename; // 充电模式名称
    private String elpiPowerinterfacename;//电桩接口方式名称
    private Integer elpiRelevancepowerstation;//所属电站
    private String elpiTell;// 联系电话
    private Integer elpiRateinformationid;//费率id
    private Integer commStatus;//连接状态
    private Integer epNum;//电桩在电站中的排序号
    private String elpiUsername;//所属用户
    private String elpiOnlinetime; // 开放时间
    private String elpiOwnprovincecode;//所属省份code
    private String elpiOwncitycode;//所属城市code
    private String elpiOwncountycode;//所属地区代码code
    private String elpiOfflinetime; // 下线时间
    private Integer elpiGateid;//GATE服务器ID
    //private Integer deleteFlag;//删除标识 父属性里已存在
    private Integer elpiOwnercompany;//桩体归属:0其他，1爱充网，2国网，3特斯拉
    private String simMac; //SIM卡卡号
    private String simPhoneNum; //SIM卡电话号码
    private Integer haveLedFlash;//LED灯(0:不支持;1:支持)
    private Integer haveGps;//是否有gps模块,0:没有，1:有
    private Integer pkConcentratorid;//集中器ID
    private Integer concentratorNum;//集中器内序号
    private String elPiParkingFee;//停车费
    private String orgNo;//运营商标识

}
