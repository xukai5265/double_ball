package com.example.web;

import com.example.domain.BlueBean;
import com.example.domain.DoubleBall;
import com.example.domain.RedBlueCount;
import com.example.service.analysis.AnalysisViewService;
import com.example.service.analysis.RedBlueCountService;
import com.example.utils.domain.BlueInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kaixu on 2018/1/11.
 */
@Component
@RestController
@RequestMapping(value = "/view")
public class AnalysisViewController {
    @Autowired
    private AnalysisViewService analysisViewService;
    @Autowired
    private RedBlueCountService redBlueCountService;


    /**
     * 获取蓝球中 - 红球的统计情况
     * @param request
     * @param blue
     * @return
     */
    @RequestMapping(value = "/red/count/{blue}/{createTime}")
    @ResponseBody
    public String viewRedBlueCountByBlue(HttpServletRequest request,@PathVariable String blue,@PathVariable String createTime){
        String callback = request.getParameter("callback");
        String startTime = createTime +" 00:00:00";
        String endTime = createTime + " 23:59:59";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<RedBlueCount> redBlueCounts = redBlueCountService.getRedBlueCountByBlue(blue,startTime,endTime);
        String json = gson.toJson(redBlueCounts);
        return callback + "(" + json + ")";
    }

    /**
     * 蓝球走势图
     * @param request
     * @return
     */
    @RequestMapping(value = "/blue/trend/{code}")
    @ResponseBody
    public String viewBlueTrend(HttpServletRequest request, @PathVariable String code){
        String callback = request.getParameter("callback");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<DoubleBall> doubleBalls = analysisViewService.getBlueTrend(code);
        BlueInfo blueInfo = new BlueInfo();
        if(doubleBalls.size()!=0){
            DoubleBall doubleBall = doubleBalls.get(0);
            List<Object> value = new ArrayList<>();
            String date = doubleBall.getDate();
            if(date.contains("(")){
                date = date.split("\\(")[0];
            }
            value.add(date);
            String blue = doubleBall.getBlue();
            if(blue.equals("01")){
                value.add(1);
            }else if(blue.equals("02")){
                value.add(2);
            }else if(blue.equals("03")){
                value.add(3);
            }else if(blue.equals("04")){
                value.add(4);
            }else if(blue.equals("05")){
                value.add(5);
            }else if(blue.equals("06")){
                value.add(6);
            }else if(blue.equals("07")){
                value.add(7);
            }else if(blue.equals("08")){
                value.add(8);
            }else if(blue.equals("09")){
                value.add(9);
            }else {
                value.add(Integer.parseInt(blue));
            }
            blueInfo.setName(date);
            blueInfo.setValue(value);
            String json = gson.toJson(blueInfo);
            return callback + "(" + json + ")";
        }else {
            String json = gson.toJson(blueInfo);
            return callback + "(" + json + ")";
        }

    }

    /**
     * 蓝球汇总信息
     * @return
     */
    @RequestMapping(value = "/blue/info/{year}")
    @ResponseBody
    public String viewBlueInfo(HttpServletRequest request,@PathVariable String year){
        String callback = request.getParameter("callback");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<DoubleBall> doubleBalls = analysisViewService.getBlueInfo(year);
        List<BlueInfo> blueInfos = new ArrayList<>();
        for(DoubleBall doubleBall : doubleBalls){
            List<Object> value = new ArrayList<>();
            String date = doubleBall.getDate();
            if(date.contains("(")){
                date = date.split("\\(")[0];
            }
            value.add(date);
            String blue = doubleBall.getBlue();
            if(blue.equals("01")){
                value.add(1);
            }else if(blue.equals("02")){
                value.add(2);
            }else if(blue.equals("03")){
                value.add(3);
            }else if(blue.equals("04")){
                value.add(4);
            }else if(blue.equals("05")){
                value.add(5);
            }else if(blue.equals("06")){
                value.add(6);
            }else if(blue.equals("07")){
                value.add(7);
            }else if(blue.equals("08")){
                value.add(8);
            }else if(blue.equals("09")){
                value.add(9);
            }else {
                value.add(Integer.parseInt(blue));
            }
            BlueInfo blueInfo = new BlueInfo();
            blueInfo.setName(date);
            blueInfo.setValue(value);
            blueInfos.add(blueInfo);
        }

        String json = gson.toJson(blueInfos);
        return callback + "(" + json + ")";
    }


    /**
     * 双色球 - 蓝球 - 各出现次数统计
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/blue/count")
    @ResponseBody
    public String viewBlueCount(HttpServletRequest request) throws IOException {
        String callback = request.getParameter("callback");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<BlueBean> blueBeans = analysisViewService.getBlueCount();
        List<String> blues = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for(BlueBean bb:blueBeans){
            blues.add(bb.getBlue());
            counts.add(Integer.parseInt(bb.getCount()));
        }
        Map<String,List> map = new HashMap<>();
        map.put("categories",blues);
        map.put("data",counts);
        String json = gson.toJson(map);
        return callback + "(" + json + ")";
    }
}
