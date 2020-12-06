package com.ssm.dto;

import lombok.Data;

@Data
public class PushOrderResponseDataDTO {
    private String StartChargeSeq;
    private int ConfirmResult;
    private String ConnectorID;
}
