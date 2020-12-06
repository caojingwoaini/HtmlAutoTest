package com.ssm.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.entity.TblChargingOrder;
import com.ssm.publicMethod.MessageManager;
import com.ssm.publicMethod.SshMethod;
import lombok.Data;

@Data
public class PushEquipStatusDataExpect {
    private ConnectorStatusInfo connectorStatusInfo;

    @Data
    public static class ConnectorStatusInfo{
        private int Status;
        private int LockStatus;
        private int ParkStatus;
        private String ConnectorID;
    }

    public static void valueof(PushEquipStatusDataExpect pushEquipStatusDataExpect, TblChargingOrder tblChargingOrder){
        ConnectorStatusInfo connectorStatusInfo=new ConnectorStatusInfo();
        connectorStatusInfo.setLockStatus(0);
        connectorStatusInfo.setParkStatus(0);
        connectorStatusInfo.setConnectorID(tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());

        String begin_charge_time=tblChargingOrder.getBeginChargetime().substring(0,17)+"*";
        String end_charge_time=tblChargingOrder.getEndChargetime();
        String cmd=String.format("grep -E \"%s[0-5][0-9]|%s\" %s |  grep \"%s\" | grep %s | grep \"%s\" ",begin_charge_time,end_charge_time,
                MessageManager.getSystemProperties("EvcsLogAddress"),"onGunWorkStatusChange4Html  is begin epCode",
                tblChargingOrder.getChorPilenumber(),"epGunNo:"+tblChargingOrder.getChorMuzzle());
        String str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                MessageManager.getSystemProperties("EvcsSshUsername"),
                MessageManager.getSystemProperties("EvcsSshPassword"),
                Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
        String str2=str.split("\n")[str.split("\n").length-1];

        JSONObject jsonObject= JSON.parseObject(str2.split("realData:")[1]);
        int status=(int)jsonObject.get("3_1");
        switch (status){
            case 0:
                connectorStatusInfo.setStatus(0);
                break;
            case 2:
                connectorStatusInfo.setStatus(1);
                break;
            case 11:
                connectorStatusInfo.setStatus(2);
                break;
            case 3:
                connectorStatusInfo.setStatus(3);
                break;
            default:
                connectorStatusInfo.setStatus(255);
                break;
        }

        pushEquipStatusDataExpect.setConnectorStatusInfo(connectorStatusInfo);
    }


    public static void main(String[] args){
        String str="2020-09-17 16:20:15,907 INFO : com.wanma.service.impl.EpServiceImpl - onGunWorkStatusChange4Html  is begin epCode:3301060019321445|epGunNo:1|realData:{\"1_1\":1,\"1_7\":0,\"2_13\":0,\"2_2\":0,\"3_1\":3,\"3_3\":5200,\"3_4\":11200,\"3_41\":0,\"3_42\":0,\"3_43\":0,\"3_44\":0,\"3_45\":0,\"3_46\":0,\"3_5\":0,\"3_6\":0,\"3_7\":0,\"4_1\":0,\"4_2\":0,\"4_3\":0,\"4_4\":0}";
        String realData=str.split("realData:")[1];
        JSONObject jsonObject= JSON.parseObject(realData);
        System.out.println(jsonObject.get("3_1").equals(3));
    }
}
