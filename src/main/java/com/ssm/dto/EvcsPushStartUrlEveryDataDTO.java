package com.ssm.dto;

public class EvcsPushStartUrlEveryDataDTO {
    //推送数据
    private String StartTime;
    private String StartChargeSeq;
    private int StartChargeSeqStat;
    private String IdentCode;
    private String ConnectorID;
    //响应结果
    private String StartChargeSeqRes;
    private int SuccStatRes;
    private int FailReasonRes;

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getStartChargeSeq() {
        return StartChargeSeq;
    }

    public void setStartChargeSeq(String startChargeSeq) {
        StartChargeSeq = startChargeSeq;
    }

    public int getStartChargeSeqStat() {
        return StartChargeSeqStat;
    }

    public void setStartChargeSeqStat(int startChargeSeqStat) {
        StartChargeSeqStat = startChargeSeqStat;
    }

    public String getIdentCode() {
        return IdentCode;
    }

    public void setIdentCode(String identCode) {
        IdentCode = identCode;
    }

    public String getConnectorID() {
        return ConnectorID;
    }

    public void setConnectorID(String connectorID) {
        ConnectorID = connectorID;
    }

    public String getStartChargeSeqRes() {
        return StartChargeSeqRes;
    }

    public void setStartChargeSeqRes(String startChargeSeqRes) {
        StartChargeSeqRes = startChargeSeqRes;
    }

    public int getSuccStatRes() {
        return SuccStatRes;
    }

    public void setSuccStatRes(int succStatRes) {
        SuccStatRes = succStatRes;
    }

    public int getFailReasonRes() {
        return FailReasonRes;
    }

    public void setFailReasonRes(int failReasonRes) {
        FailReasonRes = failReasonRes;
    }
}
