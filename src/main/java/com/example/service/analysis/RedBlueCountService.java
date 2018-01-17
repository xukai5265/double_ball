package com.example.service.analysis;

import com.example.domain.RedBlueCount;
import com.example.repository.DoubleBallJpaRepository;
import com.example.repository.DoubleBallRepository;
import com.example.repository.RedBlueCountJpaRepository;
import com.example.repository.RedBlueCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by kaixu on 2018/1/17.
 */
@Service
public class RedBlueCountService {
    @Autowired
    private RedBlueCountJpaRepository redBlueCountJpaRepository;
    @Autowired
    private RedBlueCountRepository redBlueCountRepository;


    public List<RedBlueCount> getRedBlueCountByBlue(String blue, String createTime,String endTime){
        return  redBlueCountRepository.findByBlueAndCreateTime(blue,createTime,endTime);
    }


}
