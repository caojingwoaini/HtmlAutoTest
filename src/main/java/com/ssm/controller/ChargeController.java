package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.ssm.Constant.ConstantOrgNum;
import com.ssm.dao.*;
import com.ssm.dto.*;
import com.ssm.entity.*;
import com.ssm.publicMethod.AesUtil;
import com.ssm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AutoTest")
public class ChargeController {

    @Autowired
    private TblChargingOrderMapper tblChargingOrderMapper;

    @Autowired
    private TblCompanyMapper tblCompanyMapper;

    @Autowired
    private TblPartnerMapper tblPartnerMapper;

    @Autowired
    private TblChargingrecordMapper tblChargingrecordMapper;

    @Autowired
    private TblElectricpileMapper tblElectricpileMapper;

    @Autowired
    private  TblLevelMapper tblLevelMapper;

    @Autowired
    private GetChargeMoneyExpect getChargeMoneyExpect;

    @Autowired
    private TblPurchasehistoryMapper tblPurchasehistoryMapper;

    @Autowired
    private AutoTest autoTest;

    @Autowired
    private PushUrlMap pushUrlMap2;

    @Autowired
    private PushUrlData pushUrlDataService;

    @Autowired
    private Jiemi jiemi;

    @Autowired
    private ThanPushData thanPushData;

    @Autowired
    private TblRateUniqueRelaMapper tblRateUniqueRelaMapper;

    @Autowired
    private CheckChargeMoney checkChargeMoney;

    @RequestMapping("/chargeTest")
    @ResponseBody
    public ArrayList<String> ChargeTest(ChargeParameters chargeParameters){
        //用于存放推送的url
        Map<String,String> pushUrlMap=new HashMap<>();
        //用于存放推送的数据
        Map<String,String> pushUrlData=new HashMap<>();
        boolean b=false;
        ArrayList<String> responseList=new ArrayList<String>();

        //推送数据的实际结果
        PushStartDataDTO pushStartDataDTO=new PushStartDataDTO();
        PushStartDataResponseDTO pushStartDataResponseDTO=new PushStartDataResponseDTO();
        PushChargeStatusDataDTO pushChargeStatusDataDTO=new PushChargeStatusDataDTO();
        PushChargeStatusResponseDataDTO pushChargeStatusResponseDataDTO=new PushChargeStatusResponseDataDTO();
        PushEquipStatusDataDTO pushEquipStatusDataDTO=new PushEquipStatusDataDTO();
        PushEquipStatusResponseDataDTO pushEquipStatusResponseDataDTO=new PushEquipStatusResponseDataDTO();
        PushStopDataDTO pushStopDataDTO=new PushStopDataDTO();
        PushStopResponseDataDTO pushStopResponseDataDTO=new PushStopResponseDataDTO();
        PushOrderDataDTO pushOrderDataDTO=new PushOrderDataDTO();
        PushOrderResponseDataDTO pushOrderResponseDataDTO=new PushOrderResponseDataDTO();

        //推送数据的预期结果
        PushStartDataExpect pushStartDataExpect=new PushStartDataExpect();
        PushStartDataResponseExpect pushStartDataResponseExpect=new PushStartDataResponseExpect();
        PushChargeStatusDataExpect pushChargeStatusDataExpect=new PushChargeStatusDataExpect();
        PushChargeStatusResponseDataExpect pushChargeStatusResponseDataExpect=new PushChargeStatusResponseDataExpect();
        PushEquipStatusDataExpect pushEquipStatusDataExpect=new PushEquipStatusDataExpect();
        PushEquipStatusResponseDataExpect pushEquipStatusResponseDataExpect=new PushEquipStatusResponseDataExpect();
        PushStopDataExpect pushStopDataExpect=new PushStopDataExpect();
        PushStopResponseDataExpect pushStopResponseDataExpect=new PushStopResponseDataExpect();
        PushOrderDataExpect pushOrderDataExpect=new PushOrderDataExpect();
        PushOrderResponseDataExpect pushOrderResponseDataExpect=new PushOrderResponseDataExpect();

        String str=null;
        String serviceConnection=chargeParameters.getServiceConnection();
        String chargeRadio=chargeParameters.getChargeRadio();
        String TransactionNumber=chargeParameters.getTransactionNumber();
        if (Strings.isNullOrEmpty(chargeRadio) || Strings.isNullOrEmpty(TransactionNumber)){
            responseList.add("请求参数为空");
            return responseList;
        }
        TblChargingOrder tblChargingOrder=tblChargingOrderMapper.getTblChargingOrder(TransactionNumber);
        TblCompany tblCompany=tblCompanyMapper.tblCompanyInfo(Integer.parseInt(tblChargingOrder.getChOrOrgNo()));
        TblPartner tblPartner=tblPartnerMapper.tblPartnerInfo(String.valueOf(tblCompany.getCpy_id()));
        TblPartner.checkTblPartner(tblPartner);
        TblChargingrecord tblChargingrecord=tblChargingrecordMapper.tblChargingrecordInfo(TransactionNumber);
        TblElectricpile tblElectricpile=tblElectricpileMapper.tblElectricpileInfo(tblChargingOrder.getChorPilenumber());
        TblLevel tblLevel=tblLevelMapper.tblLevelInfo(Integer.parseInt(tblChargingOrder.getChorUserid()));
        List<TblPurchasehistory> tblPurchasehistory=tblPurchasehistoryMapper.tblPurchasehistoryInfo(TransactionNumber);
        if (tblPurchasehistory.size()!=2){
            responseList.add("充电订单未结算");
            return responseList;
        }
        //预期结果类(用于校验)
        ChargeMoneyExpectDTO chargeMoneyExpectDTO=null;
        //实际结果类(用于校验)
        ChargeMoneyReallyDTO chargeMoneyReallyDTO=null;

        //充电订单是否存在个性化费率,0为不存在个性化费率，1为存在个性化费率
        int personalityFlag;
        //判断该笔充电记录是否存在个性化费率
        if (tblChargingrecord.getChreTipprice()==null){
            //无个性化费率
            //str="not have personality rate";
            personalityFlag=0;
        }else{
            //有个性化费率
            //str="have personality rate";
            personalityFlag=1;
        }
        chargeMoneyExpectDTO=getChargeMoneyExpect.chargeMoneyExpectInfo(tblChargingOrder,tblChargingrecord,new ChargeMoneyExpectDTO(),personalityFlag);
        chargeMoneyReallyDTO=getChargeMoneyExpect.chargeMoneyReallyInfo(tblChargingOrder,tblChargingrecord,tblPurchasehistory,new ChargeMoneyReallyDTO());

        //将充电金额的预期结果与实际结果进行对比
        Boolean chargeMoneyAutoTestRusult=autoTest.thanChargeMoney(chargeMoneyExpectDTO,chargeMoneyReallyDTO);
        if (chargeMoneyAutoTestRusult){
            responseList.add("充电流程测试通过");
        }else {
            responseList.add("充电流程测试失败");
            return responseList;
        }


        //推送信息部分
        try {
            if (chargeRadio.equals("evcs_charge")){
                //中电联大账户下多用户(共用一个账户)的情况
                if (ConstantOrgNum.checkConstantOrgNum(tblChargingOrder.getChOrOrgNo())){
                    pushUrlMap=pushUrlMap2.pushUrlMapInfo(tblPartner,pushUrlMap);
                    //获取已经推送出去的数据
                    pushUrlData=pushUrlDataService.PushUrlDataInfo(pushUrlMap,tblChargingOrder);
                    //启动充电结果
                    if (pushUrlData.containsKey("pushStartUrl")){
                        //实际结果
                        pushStartDataDTO= JSON.parseObject(pushUrlData.get("pushStartUrl").split("\\|")[2].replaceAll("data:",""),PushStartDataDTO.class);
                        pushStartDataResponseDTO=JSON.parseObject(jiemi.decrypt2(pushUrlData.get("pushStartUrl"),tblPartner),PushStartDataResponseDTO.class);
                        //期望结果
                        PushStartDataExpect.valueof(pushStartDataExpect,tblChargingOrder,pushUrlData.get("pushStartUrl"));
                        PushStartDataResponseExpect.valueof(pushStartDataResponseExpect,tblChargingOrder);
                        //请求参数和响应结果的实际结果与期望结果进行比较
                        b=thanPushData.ThanPushStartRequestData(pushStartDataDTO,pushStartDataExpect);
                        if (b){
                            responseList.add("推送启动充电结果的请求参数测试通过");
                        }else {
                            responseList.add("推送启动充电结果的请求参数测试失败");
                        }
                        b=thanPushData.ThanPushStartResponseData(pushStartDataResponseDTO,pushStartDataResponseExpect);
                        if (b){
                            responseList.add("推送启动充电结果的响应结果测试通过");
                        }else {
                            responseList.add("推送启动充电结果的响应结果测试失败");
                        }
                    }
                    //充电实时数据
                    if (pushUrlData.containsKey("pushChargeStatusUrl")){
                        String str2=pushUrlData.get("pushChargeStatusUrl").split("\n")[pushUrlData.get("pushChargeStatusUrl").split("\n").length-1];
                        //实际结果
                        pushChargeStatusDataDTO= JSON.parseObject(str2.split("\\|")[2].replaceAll("data:",""),PushChargeStatusDataDTO.class);
                        pushChargeStatusResponseDataDTO=JSON.parseObject(jiemi.decrypt2(str2,tblPartner),PushChargeStatusResponseDataDTO.class);
                        //期望结果
                        PushChargeStatusDataExpect.valueof(pushChargeStatusDataExpect,tblChargingOrder);
                        PushChargeStatusResponseDataExpect.valueof(pushChargeStatusResponseDataExpect,tblChargingOrder);
                        //请求参数和响应结果的实际结果与期望结果进行比较
                        b=thanPushData.ThanPushChargeStatusRequestData(pushChargeStatusDataDTO,pushChargeStatusDataExpect);
                        if (b){
                            responseList.add("推送充电实时数据的请求参数测试通过");
                        }else {
                            responseList.add("推送充电实时数据的请求参数测试失败");
                        }
                        b=thanPushData.ThanPushChargeStatusResponseData(pushChargeStatusResponseDataDTO,pushChargeStatusResponseDataExpect);
                        if (b){
                            responseList.add("推送充电实时数据的响应结果测试通过");
                        }else {
                            responseList.add("推送充电实时数据的响应结果测试失败");
                        }
                    }
                    //电桩状态数据
                    if (pushUrlData.containsKey("pushEquipStatusUrl")){
                        String str2=pushUrlData.get("pushEquipStatusUrl").split("\n")[pushUrlData.get("pushEquipStatusUrl").split("\n").length-1];
                        //实际结果
                        pushEquipStatusDataDTO= JSON.parseObject(str2.split("\\|")[2].replaceAll("data:",""),PushEquipStatusDataDTO.class);
                        pushEquipStatusResponseDataDTO=JSON.parseObject(jiemi.decrypt2(str2,tblPartner),PushEquipStatusResponseDataDTO.class);
                        //期望结果
                        PushEquipStatusDataExpect.valueof(pushEquipStatusDataExpect,tblChargingOrder);
                        PushEquipStatusResponseDataExpect.valueof(pushEquipStatusResponseDataExpect);
                        //请求参数和响应结果的实际结果与期望结果进行比较
                        b=thanPushData.ThanPushEquipStatusRequestData(pushEquipStatusDataDTO,pushEquipStatusDataExpect);
                        if (b){
                            responseList.add("推送设备状态数据的请求参数测试通过");
                        }else {
                            responseList.add("推送设备状态数据的请求参数测试失败");
                        }
                        b=thanPushData.ThanPushEquipStatusResponseData(pushEquipStatusResponseDataDTO,pushEquipStatusResponseDataExpect);
                        if (b){
                            responseList.add("推送设备状态数据的响应结果测试通过");
                        }else {
                            responseList.add("推送设备状态数据的响应结果测试失败");
                        }
                    }
                    //停止充电结果数据
                    if (pushUrlData.containsKey("pushStopUrl")){
                        //实际结果
                        pushStopDataDTO= JSON.parseObject(pushUrlData.get("pushStopUrl").split("\\|")[2].replaceAll("data:",""),PushStopDataDTO.class);
                        pushStopResponseDataDTO=JSON.parseObject(jiemi.decrypt2(pushUrlData.get("pushStopUrl"),tblPartner),PushStopResponseDataDTO.class);
                        //期望结果
                        PushStopDataExpect.valueof(pushStopDataExpect,tblChargingOrder);
                        PushStopResponseDataExpect.valueof(pushStopResponseDataExpect,tblChargingOrder);
                        //请求参数和响应结果的实际结果与期望结果进行比较
                        b=thanPushData.ThanPushStopRequestData(pushStopDataDTO,pushStopDataExpect);
                        if (b){
                            responseList.add("推送停止充电结果的请求参数测试通过");
                        }else {
                            responseList.add("推送停止充电结果的请求参数测试失败");
                        }
                        b=thanPushData.ThanPushStopResponseData(pushStopResponseDataDTO,pushStopResponseDataExpect);
                        if (b){
                            responseList.add("推送停止充电结果的响应结果测试通过");
                        }else {
                            responseList.add("推送停止充电结果的响应结果测试失败");
                        }
                    }
                    //充电订单数据
                    if (pushUrlData.containsKey("pushOrderUrl")){
                        //实际结果
                        pushOrderDataDTO= JSON.parseObject(pushUrlData.get("pushOrderUrl").split("\\|")[2].replaceAll("data:",""),PushOrderDataDTO.class);
                        pushOrderResponseDataDTO=JSON.parseObject(jiemi.decrypt2(pushUrlData.get("pushOrderUrl"),tblPartner),PushOrderResponseDataDTO.class);
                        //期望结果
                        PushOrderDataExpect.valueof(pushOrderDataExpect,tblChargingOrder);
                        PushOrderResponseDataExpect.valueof(pushOrderResponseDataExpect,tblChargingOrder);
                        //请求参数和响应结果的实际结果与期望结果进行比较
                        b=thanPushData.ThanPushOrderRequestData(pushOrderDataDTO,pushOrderDataExpect);
                        if (b){
                            responseList.add("推送充电订单数据的请求参数测试通过");
                        }else {
                            responseList.add("推送充电订单数据的请求参数测试失败");
                        }
                        b=thanPushData.ThanPushOrderResponseData(pushOrderResponseDataDTO,pushOrderResponseDataExpect);
                        if (b){
                            responseList.add("推送充电订单数据的响应结果测试通过");
                        }else {
                            responseList.add("推送充电订单数据的响应结果测试失败");
                        }
                    }
                }else {
                    responseList.add("没有此项充电业务");
                    return responseList;
                }
            }else {
                responseList.add("充电类型不存在");
                return responseList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        responseList.add("推送数据测试通过");
        return responseList;
    }
}
