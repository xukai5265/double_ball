package com.example.service.crawler;

import com.example.domain.BlueBean;
import com.example.repository.DoubleBallJpaRepository;
import com.example.repository.DoubleBallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaixu on 2018/1/11.
 */
@Service
public class AnalysisViewService {
    @Autowired
    private DoubleBallJpaRepository doubleBallJpaRepository;
    @Autowired
    private DoubleBallRepository doubleBallRepository;

    public List<BlueBean> getBlueCount(){
        List<BlueBean> blueBeans = new ArrayList<>();
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("01")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("02")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("03")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("04")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("05")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("06")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("07")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("08")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("09")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("10")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("11")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("12")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("13")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("14")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("15")+""));
        blueBeans.add(new BlueBean("11",doubleBallRepository.countByBlue("16")+""));
        return blueBeans;
    }
}
