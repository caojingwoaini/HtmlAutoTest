package com.ssm.dao;

import com.ssm.entity.TblRateUniqueRela;
import org.apache.ibatis.annotations.Param;

public interface TblRateUniqueRelaMapper {

    public TblRateUniqueRela tblRateUniqueRelaInfo(@Param("cpyId") int cpyId,@Param("electricpileId") int electricpileId,@Param("levelId") int levelId);

}
