package com.example.repository;

import com.example.domain.DoubleBall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kaixu on 2018/1/11.
 */
public interface DoubleBallRepository extends Repository<DoubleBall, Long> {

    List<DoubleBall> findByCode(String code);

    List<DoubleBall> findByRed(String red);

    @Query(value="select * from double_ball a where a.code like CONCAT('%',:year,'%') ORDER BY a.code DESC limit 1000",nativeQuery=true)
    List<DoubleBall> findByCodeLike(@Param("year") String year);

    @Query(value="select * from double_ball a ORDER BY a.code DESC",nativeQuery=true)
    List<DoubleBall> findAll();

    int countByBlue(String blue);

}
