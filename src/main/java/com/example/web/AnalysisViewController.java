package com.example.web;

import com.example.domain.BlueBean;
import com.example.service.crawler.AnalysisViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kaixu on 2018/1/11.
 */
@Component
@RestController
@RequestMapping(value = "/view")
public class AnalysisViewController {
    @Autowired
    private AnalysisViewService analysisViewService;

    @RequestMapping(value = "/blue/count")
    public List<BlueBean> viewBlueCount(){
        return analysisViewService.getBlueCount();
    }
}
