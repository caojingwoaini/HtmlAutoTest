package com.ssm.dto;

import lombok.Data;

@Data
public class PushEquipStatusDataDTO {
    private ConnectorStatusInfo connectorStatusInfo;

    @Data
    public static class ConnectorStatusInfo{
        private int Status;
        private int LockStatus;
        private int ParkStatus;
        private String ConnectorID;
    }

}
