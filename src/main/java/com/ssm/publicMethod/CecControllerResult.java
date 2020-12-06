package com.ssm.publicMethod;

import com.google.gson.GsonBuilder;
import com.ssm.util.HmacMd5Util;

public class CecControllerResult {
    private String Msg;
    private String Data;
    private String Sig;
    private Integer Ret;

    public static String resultError(int ret,String msg){
        CecControllerResult cecControllerResult=new CecControllerResult();
        cecControllerResult.setMsg(msg);
        cecControllerResult.setData("");
        cecControllerResult.setSig(null);
        cecControllerResult.setRet(ret);
        return new GsonBuilder().disableHtmlEscaping().create().toJson(cecControllerResult);
    }

    public static String resultSuccess(int ret, String msg, String data, String key, String sig){
        CecControllerResult cecControllerResult=new CecControllerResult();
        cecControllerResult.setRet(ret);
        cecControllerResult.setData(data);
        cecControllerResult.setMsg(msg);
        cecControllerResult.setSig(HmacMd5Util.getHmacMd5Str(sig, key));
        return new GsonBuilder().disableHtmlEscaping().create().toJson(cecControllerResult);
    }



    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getSig() {
        return Sig;
    }

    public void setSig(String sig) {
        Sig = sig;
    }

    public Integer getRet() {
        return Ret;
    }

    public void setRet(Integer ret) {
        Ret = ret;
    }

    @Override
    public String toString() {
        return "CecControllerResult{" +
                "Msg='" + Msg + '\'' +
                ", Data='" + Data + '\'' +
                ", Sig='" + Sig + '\'' +
                ", Ret=" + Ret +
                '}';
    }
}
