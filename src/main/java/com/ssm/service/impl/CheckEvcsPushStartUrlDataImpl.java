package com.ssm.service.impl;

import com.google.common.base.Strings;
import com.ssm.dao.TblChargingOrderMapper;
import com.ssm.publicMethod.MessageManager;
import com.ssm.publicMethod.SshMethod;
import com.ssm.service.CheckEvcsPushData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckEvcsPushStartUrlDataImpl {

    @Autowired
    private TblChargingOrderMapper tblChargingOrderMapper;



    //校验evcs推送充电结果的数据是否正确
//    public boolean checkPushStartUrl(String pushService,String PushStartUrl,String TransactionNumber,int cpyId){
//        boolean PushStartUrlResult;
//        String LogAddress="";
//        String LogName="";
//        TblChargingOrder tblChargingOrder =tblChargingOrderMapper.getTblChargingOrder(TransactionNumber);
//        //第三方流水号
//        String chor_parter_extradata=tblChargingOrder.getChor_parter_extradata();
//        if (pushService.equals("htmlevcs")){
//            //获取日志文件的路径
//            LogAddress= MessageManager.getSystemProperties("EvcsLogAddress");
//            //获取日志文件的名称
//            LogName=MessageManager.getSystemProperties("EvcsLogName");
//        }else{
//            return false;
//        }
//        String PushStartUrlData= SshMethod.exec(MessageManager.getSystemProperties("EvcsSshIp"),
//                MessageManager.getSystemProperties("EvcsSshUsername"),
//                MessageManager.getSystemProperties("EvcsSshPassword"),
//                Integer.parseInt(MessageManager.getSystemProperties("EvcsSshPort")),
//                String.format("cd %s\ngrep \"%s\" %s | grep %s",LogAddress,PushStartUrl,LogName,
//                        chor_parter_extradata)
//                );
//        if (Strings.isNullOrEmpty(PushStartUrlData)){
//            return false;
//        }
//        //PushStartUrlResult=checkEvcsPushEveryData.checkEvcsPushStartUrlEveryData(PushStartUrlData,cpyId);
//        return true;
//    }

}
