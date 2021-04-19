package com.ssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.dto.ChargeMoneyExpectDTO;
import com.ssm.dto.ChargeMoneyReallyDTO;
import com.ssm.entity.TblChargingOrder;
import com.ssm.entity.TblChargingrecord;
import com.ssm.entity.TblPurchasehistory;
import com.ssm.service.GetChargeMoneyExpect;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GetChargeMoneyExpectImpl implements GetChargeMoneyExpect {

    public ChargeMoneyExpectDTO chargeMoneyExpectInfo(TblChargingOrder tblChargingOrder, TblChargingrecord tblChargingrecord,ChargeMoneyExpectDTO chargeMoneyExpectDTO,int personalityFlag) {
        //电桩默认费率的尖峰平谷电费费率
        BigDecimal chRe_JPrice=tblChargingrecord.getChreJprice();
        BigDecimal chRe_FPrice=tblChargingrecord.getChreFprice();
        BigDecimal chRe_PPrice=tblChargingrecord.getChrePprice();
        BigDecimal chRe_GPrice=tblChargingrecord.getChreGprice();
        //电桩默认费率的尖峰平谷服务费费率
        BigDecimal chRe_JMoney=tblChargingrecord.getChreJmoney();
        BigDecimal chRe_FMoney=tblChargingrecord.getChreFmoney();
        BigDecimal chRe_PMoney=tblChargingrecord.getChrePmoney();
        BigDecimal chRe_GMoney=tblChargingrecord.getChreGmoney();

        //个性化费率的尖峰平谷电费费率
        BigDecimal chreTipprice=tblChargingrecord.getChreTipprice();
        BigDecimal chrePeakprice=tblChargingrecord.getChrePeakprice();
        BigDecimal chreFlatprice=tblChargingrecord.getChreFlatprice();
        BigDecimal chreValleyprice=tblChargingrecord.getChreValleyprice();
        //个性化费率的尖峰平谷服务费费率
        BigDecimal chreTipmoney=tblChargingrecord.getChreTipmoney();
        BigDecimal chrePeakmoney=tblChargingrecord.getChrePeakmoney();
        BigDecimal chreFlatmoney=tblChargingrecord.getChreFlatmoney();
        BigDecimal chreValleymoney=tblChargingrecord.getChreValleymoney();

        //获取充电记录对应的费率信息
        String chre_Quantumdate=tblChargingrecord.getChreQuantumdate();
        //[{"st":"0","mark":"4","et":"360"},{"st":"360","mark":"2","et":"720"},{"st":"720","mark":"3","et":"1080"},{"st":"1080","mark":"4","et":"1440"}]
        JSONArray jsonArray=JSON.parseObject(chre_Quantumdate).getJSONArray("data");

        //获取充电订单的尖峰平谷电量
        BigDecimal chOr_tipPower=tblChargingOrder.getChOr_tipPower().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal chOr_peakPower=tblChargingOrder.getChOr_peakPower().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal chOr_usualPower=tblChargingOrder.getChOr_usualPower().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal chOr_valleyPower=tblChargingOrder.getChOr_valleyPower().setScale(4,BigDecimal.ROUND_HALF_UP);

        //充电订单的电费、服务费、总金额、预冻结金额、退款金额
        BigDecimal chorChargemoneyExcept=null;
        BigDecimal chorServicemoneyExcept=null;
        BigDecimal chorMoenyExcept=null;
        //如果不存在个性化费率
        if (personalityFlag==0){
            chorChargemoneyExcept=chOr_tipPower.multiply(chRe_JPrice).add(chOr_peakPower.multiply(chRe_FPrice)).
                    add(chOr_usualPower.multiply(chRe_PPrice)).add(chOr_valleyPower.multiply(chRe_GPrice)).setScale(4,BigDecimal.ROUND_HALF_UP);
            chorServicemoneyExcept=chOr_tipPower.multiply(chRe_JMoney).add(chOr_peakPower.multiply(chRe_FMoney)).
                    add(chOr_usualPower.multiply(chRe_PMoney)).add(chOr_valleyPower.multiply(chRe_GMoney)).setScale(4,BigDecimal.ROUND_HALF_UP);
            chorMoenyExcept=chorChargemoneyExcept.add(chorServicemoneyExcept).setScale(4,BigDecimal.ROUND_HALF_UP);
        }else{
            //如果存在个性化费率
            chorChargemoneyExcept=chOr_tipPower.multiply(chreTipprice).add(chOr_peakPower.multiply(chrePeakprice)).
                    add(chOr_usualPower.multiply(chreFlatprice)).add(chOr_valleyPower.multiply(chreValleyprice)).setScale(4,BigDecimal.ROUND_HALF_UP);
            chorServicemoneyExcept=chOr_tipPower.multiply(chreTipmoney).add(chOr_peakPower.multiply(chrePeakmoney)).
                    add(chOr_usualPower.multiply(chreFlatmoney)).add(chOr_valleyPower.multiply(chreValleymoney)).setScale(4,BigDecimal.ROUND_HALF_UP);
            chorMoenyExcept=chorChargemoneyExcept.add(chorServicemoneyExcept).setScale(4,BigDecimal.ROUND_HALF_UP);
        }
        BigDecimal FrozenMoneyExcept=tblChargingrecord.getChreFrozenamt().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal RefundMoneyExcept=FrozenMoneyExcept.subtract(chorMoenyExcept).setScale(4,BigDecimal.ROUND_HALF_UP);

        chargeMoneyExpectDTO.setTotalMoney(chorMoenyExcept);
        chargeMoneyExpectDTO.setChargeMoney(chorChargemoneyExcept);
        chargeMoneyExpectDTO.setServiceMoney(chorServicemoneyExcept);
        chargeMoneyExpectDTO.setFrozenMoney(FrozenMoneyExcept);
        chargeMoneyExpectDTO.setRefundMoney(RefundMoneyExcept);
        return chargeMoneyExpectDTO;
    }

    public ChargeMoneyReallyDTO chargeMoneyReallyInfo(TblChargingOrder tblChargingOrder, TblChargingrecord tblChargingrecord, List<TblPurchasehistory> tblPurchasehistory, ChargeMoneyReallyDTO chargeMoneyReallyDTO) {
        BigDecimal chorChargemoneyReally=tblChargingOrder.getChorChargemoney().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal chorServicemoneyReally=tblChargingOrder.getChorServicemoney().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal chorMoenyReally=tblChargingOrder.getChorMoeny().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal FrozenMoneyReally=tblChargingrecord.getChreFrozenamt().setScale(4,BigDecimal.ROUND_HALF_UP);
        BigDecimal RefundMoneyReally=tblPurchasehistory.get(1).getPuHi_Monetary().setScale(4,BigDecimal.ROUND_HALF_UP);
        chargeMoneyReallyDTO.setTotalMoney(chorMoenyReally);
        chargeMoneyReallyDTO.setChargeMoney(chorChargemoneyReally);
        chargeMoneyReallyDTO.setServiceMoney(chorServicemoneyReally);
        chargeMoneyReallyDTO.setFrozenMoney(FrozenMoneyReally);
        chargeMoneyReallyDTO.setRefundMoney(RefundMoneyReally);
        return chargeMoneyReallyDTO;
    }

    //    public static void main(String[] args){
//        String chre_Quantumdate="{\"data\":[{\"st\":\"0\",\"et\":\"360\",\"mark\":\"4\"},{\"st\":\"360\",\"et\":\"720\",\"mark\":\"2\"},{\"st\":\"720\",\"et\":\"1080\",\"mark\":\"3\"},{\"st\":\"1080\",\"et\":\"1440\",\"mark\":\"4\"}]}";
//        JSONObject jsonObject= JSON.parseObject(chre_Quantumdate);
//        JSONArray jsonArray=jsonObject.getJSONArray("data");
//        System.out.println(jsonArray);
//        JSONObject jsonObject2=(JSONObject)jsonArray.get(0);
//        String str=jsonObject2.getString("et");
//        System.out.println(str.equals(360));
//        System.out.println(str.equals("360"));
//    }

}
