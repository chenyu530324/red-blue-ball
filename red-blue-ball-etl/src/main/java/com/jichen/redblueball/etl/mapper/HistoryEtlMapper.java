package com.jichen.redblueball.etl.mapper;

import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.HistorySum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryEtlMapper {

    @Select("SELECT * FROM RBB_HISTORY")
    List<History> extractHistoryData();

    @Insert("INSERT INTO RBB_HISTORY_ETL_SUM(NUMBER,SUM) VALUE(#{number,jdbcType=INTEGER},#{sum,jdbcType=INTEGER})")
    void insertHistorySum(HistorySum historySum);
}
