package com.ssm.service.impl;

import com.google.common.base.Strings;
import com.ssm.entity.TblChargingOrder;
import com.ssm.publicMethod.MessageManager;
import com.ssm.publicMethod.SshMethod;
import com.ssm.service.PushUrlData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PushUrlDataImpl implements PushUrlData {
    String str;
    String cmd;
    String begin_charge_time;
    String end_charge_time;

    @Override
    public Map<String,String> PushUrlDataInfo(Map<String, String> pushUrlMap, TblChargingOrder tblChargingOrder) {
        Map<String,String> pushUrlData=new HashMap<String,String>();
        if (!Strings.isNullOrEmpty(pushUrlMap.get("pushStartUrl"))){
            cmd=String.format("grep \"%s\" %s  | grep %s",pushUrlMap.get("pushStartUrl"),
                    MessageManager.getSystemProperties("EvcsLogAddress"),tblChargingOrder.getChorParterExtradata());
            str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                    MessageManager.getSystemProperties("EvcsSshUsername"),
                    MessageManager.getSystemProperties("EvcsSshPassword"),
                    Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
            pushUrlData.put("pushStartUrl",str);
        }
        if (!Strings.isNullOrEmpty(pushUrlMap.get("pushChargeStatusUrl"))){
            cmd=String.format("grep \"%s\" %s  | grep %s",pushUrlMap.get("pushChargeStatusUrl"),
                    MessageManager.getSystemProperties("EvcsLogAddress"),tblChargingOrder.getChorParterExtradata());
            str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                    MessageManager.getSystemProperties("EvcsSshUsername"),
                    MessageManager.getSystemProperties("EvcsSshPassword"),
                    Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
            pushUrlData.put("pushChargeStatusUrl",str);
        }
        if (!Strings.isNullOrEmpty(pushUrlMap.get("pushEquipStatusUrl"))){
            begin_charge_time=tblChargingOrder.getBeginChargetime().substring(0,17)+"*";
            end_charge_time=tblChargingOrder.getEndChargetime();
            cmd=String.format("grep -E \"%s[0-5][0-9]|%s\" %s |  grep \"%s\" | grep %s",begin_charge_time,end_charge_time,
                    MessageManager.getSystemProperties("EvcsLogAddress"),pushUrlMap.get("pushEquipStatusUrl"),
                    tblChargingOrder.getChorPilenumber()+"0"+tblChargingOrder.getChorMuzzle());
            str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                    MessageManager.getSystemProperties("EvcsSshUsername"),
                    MessageManager.getSystemProperties("EvcsSshPassword"),
                    Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
            pushUrlData.put("pushEquipStatusUrl",str);
        }
        if (!Strings.isNullOrEmpty(pushUrlMap.get("pushStopUrl"))){
            cmd=String.format("grep \"%s\" %s  | grep %s",pushUrlMap.get("pushStopUrl"),
                    MessageManager.getSystemProperties("EvcsLogAddress"),tblChargingOrder.getChorParterExtradata());
            str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                    MessageManager.getSystemProperties("EvcsSshUsername"),
                    MessageManager.getSystemProperties("EvcsSshPassword"),
                    Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
            pushUrlData.put("pushStopUrl",str);
        }
        if (!Strings.isNullOrEmpty(pushUrlMap.get("pushOrderUrl"))){
            cmd=String.format("grep \"%s\" %s  | grep %s",pushUrlMap.get("pushOrderUrl"),
                    MessageManager.getSystemProperties("EvcsLogAddress"),tblChargingOrder.getChorParterExtradata());
            str= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
                    MessageManager.getSystemProperties("EvcsSshUsername"),
                    MessageManager.getSystemProperties("EvcsSshPassword"),
                    Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),cmd);
            pushUrlData.put("pushOrderUrl",str);
        }
        return pushUrlData;
    }



    public static void main(String[] args){
        String str="2020-08-27 15:46:46";
        String str2=str.substring(0,17)+"*";
        System.out.println(str2);
    }
}
