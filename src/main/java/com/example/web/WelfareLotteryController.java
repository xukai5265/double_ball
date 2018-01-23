package com.example.web;

import com.example.domain.DoubleBall;
import com.example.service.analysis.AnalysisViewService;
import com.example.service.crawler.WelfareLotteryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kaixu on 2018/1/11.
 * 福彩
 */
@Component
@RestController
@RequestMapping(value = "/doubel_ball")
@EnableScheduling
public class WelfareLotteryController {
    public static final Logger log = LoggerFactory.getLogger(WelfareLotteryController.class);

    @Autowired
    private WelfareLotteryService welfareLotteryService;

    @Autowired
    private AnalysisViewService analysisViewService;

    /**
     * 爬去双色球历史数据
     */
    @RequestMapping(value = "/save")
    public void crawlerDoubleBallHistoryData()
    {
        try {
            welfareLotteryService.doubleBallCrawler("http://www.cwl.gov.cn/cwl_admin/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=&issueEnd=&dayStart=2012-10-24&dayEnd=2018-01-11&pageNo=");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 每12个小时爬取一次
     * @throws IOException
     */
    @Scheduled(fixedRate=1000*60*60*12)
    public void incrementCrawlerDoubleBallData() throws IOException {
        System.out.println("开始增量爬取...");
        welfareLotteryService.incrementCrawlerDoubleBallData();
    }

    /**
     * 随机生成一注彩票
     * @return
     */
    @RequestMapping(value = "gen")
    public String genDoubleBall(HttpServletRequest request){
        String callback = request.getParameter("callback");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String,Object> res = welfareLotteryService.randomDoubleBall();
        String json = gson.toJson(res);
        return callback + "(" + json + ")";
    }

    @RequestMapping(value = "test/{code}")
    public String test(@PathVariable String code){
        long index = 0;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        boolean flag = true;
        List<DoubleBall> doubleBalls = analysisViewService.getBlueTrend(code);
        DoubleBall doubleBall = doubleBalls.get(0);
        while(flag){
            Map<String,Object> res = welfareLotteryService.randomDoubleBall();
            String reds = "";
            List<Integer> redArr = (List<Integer>)res.get("red");
            for(int i:redArr){
                if(i<10){
                    reds+="0"+i+",";
                }else {
                    reds+=i+",";
                }
            }
            if(reds.endsWith(",")){
                reds=reds.substring(0,reds.length()-1);
            }
            String blue = (Integer)res.get("blue")<10? "0"+res.get("blue"):res.get("blue")+"";
            if(reds.equals(doubleBall.getRed())&& blue.equals(doubleBall.getBlue())){
                flag = false;
                Map<String,Object> map = new HashMap<>();
                map.put("index",index);
                map.put("随机：",reds+"--"+blue);
                map.put("中奖号码：：",doubleBall.getRed()+"--"+doubleBall.getBlue());
                String json = gson.toJson(map);
                log.info(json);
                return json;
            }
            index++;
        }
        return "";

    }
}
