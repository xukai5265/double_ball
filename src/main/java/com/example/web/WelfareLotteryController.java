package com.example.web;

import com.example.service.crawler.WelfareLotteryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public Map<String,List<Integer>> genDoubleBall(){
        return welfareLotteryService.randomDoubleBall();
    }
}
