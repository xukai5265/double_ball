package com.example.repository;

import com.example.domain.DoubleBall;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by kaixu on 2018/1/11.
 */
public interface DoubleBallRepository extends Repository<DoubleBall, Long> {

    List<DoubleBall> findByCode(String code);

    List<DoubleBall> findByRed(String red);

    List<DoubleBall> findAll();

    int countByBlue(String blue);

}
