package com.example.repository;

import com.example.domain.RedBlueCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kaixu on 2018/1/17.
 */
public interface RedBlueCountRepository extends Repository<RedBlueCount, Long> {
    @Query(value="select * from double_ball_blue_red_relations a where a.blue = :blue AND a.create_time BETWEEN :startTime AND :endTime",nativeQuery=true)
    List<RedBlueCount> findByBlueAndCreateTime(@Param("blue") String blue,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
