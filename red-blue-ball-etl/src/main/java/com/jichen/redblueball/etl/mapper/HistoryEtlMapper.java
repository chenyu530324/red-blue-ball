package com.jichen.redblueball.etl.mapper;

import com.jichen.redblueball.common.BallType;
import com.jichen.redblueball.common.model.ArithmeticEtl;
import com.jichen.redblueball.common.model.History;
import com.jichen.redblueball.common.model.HistorySum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryEtlMapper {

    @Select("SELECT * FROM RBB_HISTORY")
    List<History> queryHistoryData();

    @Insert("INSERT INTO RBB_HISTORY_ETL_SUM(NUMBER,SUM) VALUE(#{number,jdbcType=INTEGER},#{sum,jdbcType=INTEGER})")
    void insertHistorySum(HistorySum historySum);

    @Insert("INSERT INTO RBB_ARITHMETIC_ETL(ARITHMETIC_NAME,BALL_TYPE,CURRENT_NUMBER,CURRENT_BALLS,ARITHMETIC_RESULT,RESULT)"
            + "VALUES(#{arithmeticName,jdbcType=VARCHAR},#{ballType,jdbcType=VARCHAR},#{currentNumber,jdbcType=INTEGER},"
            + "#{currentBalls,jdbcType=VARCHAR},#{arithmeticResult,jdbcType=VARCHAR},#{result,jdbcType=INTEGER})")
    void insertArithmetic(ArithmeticEtl arithmetic);

    @Delete("DELETE FROM RBB_ARITHMETIC_ETL WHERE ARITHMETIC_NAME = #{name} and BALL_TYPE = #{type}")
    void deleteExistArithmeticEtlResult(@Param("name") String name, @Param("type") BallType type);
}
