package com.ssm.dto;

import lombok.Data;

@Data
public class PushEquipStatusResponseDataExpect {
    private int Status;

    public static void valueof(PushEquipStatusResponseDataExpect pushEquipStatusResponseDataExpect){
        pushEquipStatusResponseDataExpect.setStatus(0);
    }
}
