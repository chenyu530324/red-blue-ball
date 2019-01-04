package com.jichen.redblueball.importer.mapper;

import com.jichen.redblueball.common.model.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {

    @Insert("INSERT INTO RBB_HISTORY (NUMBER, DATE, RED1, RED2, RED3, RED4, RED5, RED6, BLUE)"
            + "VALUE (#{number,jdbcType=INTEGER},#{date,jdbcType=DATE},#{red1.value,jdbcType=INTEGER},"
            + "#{red2.value,jdbcType=INTEGER},#{red3.value,jdbcType=INTEGER},#{red4.value,jdbcType=INTEGER},#{red5.value,jdbcType=INTEGER},"
            + "#{red6.value,jdbcType=INTEGER},#{blue.value,jdbcType=INTEGER})")
    void insertHistory(History history);

}
