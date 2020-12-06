package com.ssm.dto;

import com.alibaba.fastjson.JSON;
import com.ssm.entity.TblChargingOrder;
import com.ssm.publicMethod.MessageManager;
import com.ssm.publicMethod.SshMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Data
public class PushChargeStatusDataExpect {



    private BigDecimal TotalPower;
    private BigDecimal TotalMoney;
    private String StartTime;
    private String EndTime;
    private int Soc;
    private int ConnectorStatus;
    private BigDecimal CurrentA;
    private BigDecimal VoltageA;
    private String StartChargeSeq;
    private int StartChargeSeqStat;
    private String ConnectorID;
    private BigDecimal ElecMoney;
    private BigDecimal SeviceMoney;



    public static void valueof(PushChargeStatusDataExpect pushChargeStatusDataExpect, TblChargingOrder tblChargingOrder){
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String cmd=String.format("grep \"onRealData is begin\" %s  | grep %s",
                MessageManager.getSystemProperties("EvcsLogAddress"),tblChargingOrder.getChorParterExtradata());
        String str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                MessageManager.getSystemProperties("EvcsSshUsername"),
                MessageManager.getSystemProperties("EvcsSshPassword"),
                Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
        String str2=str.split("\n")[str.split("\n").length-1].split("realData:")[1].replaceAll("=",":");
        PushChargeStatusDataExpectFz pushChargeStatusDataExpectFz= JSON.parseObject(str2,PushChargeStatusDataExpectFz.class);

        pushChargeStatusDataExpect.setTotalPower(new BigDecimal(Double.valueOf(pushChargeStatusDataExpectFz.getChargeMeterNum())/1000).setScale(2, BigDecimal.ROUND_HALF_UP));
        pushChargeStatusDataExpect.setTotalMoney(new BigDecimal(Double.valueOf(pushChargeStatusDataExpectFz.getChargeAmt())/100).setScale(2, BigDecimal.ROUND_HALF_UP));
        pushChargeStatusDataExpect.setStartTime(format.format(Long.valueOf(pushChargeStatusDataExpectFz.getStartTime())*1000));
        pushChargeStatusDataExpect.setEndTime(str.split("\n")[str.split("\n").length-1].split(",")[0]);
        pushChargeStatusDataExpect.setSoc(Integer.valueOf(pushChargeStatusDataExpectFz.getSoc()));
        pushChargeStatusDataExpect.setConnectorStatus(3);
        pushChargeStatusDataExpect.setCurrentA(new BigDecimal(Double.valueOf(pushChargeStatusDataExpectFz.getOutCurrent())/100).setScale(2, BigDecimal.ROUND_HALF_UP));
        pushChargeStatusDataExpect.setVoltageA(new BigDecimal(Double.valueOf(pushChargeStatusDataExpectFz.getOutVol())/10).setScale(2, BigDecimal.ROUND_HALF_UP));
        pushChargeStatusDataExpect.setStartChargeSeq(tblChargingOrder.getChorParterExtradata());
        pushChargeStatusDataExpect.setStartChargeSeqStat(2);
        pushChargeStatusDataExpect.setConnectorID(tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());
    }
}
