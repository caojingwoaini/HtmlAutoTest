package com.ssm.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TblChargingrecord {
    private Long pkChargingrecord;

    private Integer chreElectricpileid;

    private String chreUsingmachinecode;

    private String chreTransactionnumber;

    private String chreReservationnumber;

    private String chreStartdate;

    private Integer chreChargingnumber;

    private Integer chreChargingmethod;

    private Integer chreResttime;

    private String chreEnddate;

    private String chreCode;

    private String chreBeginshowsnumber;

    private String chreEndshowsnumber;

    private String userPhone;

    private String userId;

    private Integer chreUserorigin;

    /**
     * 充电状态,0:开始充电;1:结束充电;2:接受到充电客户的命令;3:充电失败,4:等待插枪;5:临时结算;6:结算;7:结算完成;
     */
    private Integer chreStatus;

    private Integer chreStopcause;

    private BigDecimal chreJprice;

    private BigDecimal chreFprice;

    private BigDecimal chrePprice;

    private BigDecimal chreGprice;

    private String chreQuantumdate;

    private BigDecimal chreFrozenamt;

    private BigDecimal chreRealamt;

    private Integer pkUsercard;

    private Integer chrePaymode;

    private Integer chreOrgno;

    private BigDecimal chreServicecharge;

    private Integer chreThirdcode;

    private Short chreThirdtype;

    private String chreParterUserLogo;

    private String chreParterExtradata;

    private String chreUsrgateip;

    private BigDecimal chreJmoney;

    private BigDecimal chreFmoney;

    private BigDecimal chrePmoney;

    private BigDecimal chreGmoney;

    private Long accountId;

    private BigDecimal chreTipprice;

    private BigDecimal chrePeakprice;

    private BigDecimal chreFlatprice;

    private BigDecimal chreValleyprice;

    private BigDecimal chreTipmoney;

    private BigDecimal chrePeakmoney;

    private BigDecimal chreFlatmoney;

    private BigDecimal chreValleymoney;
}
