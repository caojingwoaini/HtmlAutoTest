package com.ssm.dto;

import lombok.Data;

@Data
public class PushStopDataDTO {
    private String StartChargeSeq;
    private int SuccStat;
    private int StartChargeSeqStat;
    private int FailReason;
    private String ConnectorID;
}
