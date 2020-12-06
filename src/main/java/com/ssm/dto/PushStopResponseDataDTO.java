package com.ssm.dto;

import lombok.Data;

@Data
public class PushStopResponseDataDTO {
    private String StartChargeSeq;
    private int SuccStat;
    private int FailReason;
}
