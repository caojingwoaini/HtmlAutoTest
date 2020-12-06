package com.ssm.dto;

import lombok.Data;

@Data
public class PushStartDataResponseDTO {
    //推送启动充电结果的响应结果
    private String StartChargeSeq;
    private int SuccStat;
    private int FailReason;
}
