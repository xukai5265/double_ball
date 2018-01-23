package com.example.service.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.DoubleBall;
import com.example.repository.DoubleBallJpaRepository;
import com.example.repository.DoubleBallRepository;
import com.example.utils.HttpRequest;
import com.example.utils.JsoupHelper;
import com.example.utils.WebCrawler;
import com.example.web.WelfareLotteryController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kaixu on 2018/1/11.
 * 福利彩票爬取程序
 */
@Service
public class WelfareLotteryService {
    public static final Logger log = LoggerFactory.getLogger(WelfareLotteryService.class);
    @Autowired
    private DoubleBallJpaRepository doubleBallJpaRepository;
    @Autowired
    private DoubleBallRepository doubleBallRepository;


    /**
     * 增量获取
     */
    public void incrementCrawlerDoubleBallData() throws IOException {
        // open baidu
        String url ="https://www.baidu.com/s?wd=%E5%8F%8C%E8%89%B2%E7%90%83";
        WebClient webClient = WebCrawler.getInstance().getNewWebClient();
        HtmlPage page = webClient.getPage(url);
        String firstPageHtml = page.asXml();
        Document doc = Jsoup.parse(firstPageHtml);
        Elements elements = doc.select("#1 > div.c-border.c-gap-bottom-small > div.c-gap-top.c-gap-bottom.op_caipiao_ball.c-clearfix");
        String red1 = elements.select("span:nth-child(1)").text();
        String red2 = elements.select("span:nth-child(2)").text();
        String red3 = elements.select("span:nth-child(3)").text();
        String red4 = elements.select("span:nth-child(4)").text();
        String red5 = elements.select("span:nth-child(5)").text();
        String red6 = elements.select("span:nth-child(6)").text();
        String bule = elements.select("span.c-icon.c-icon-ball-blue.op_caipiao_ball_blue.c-gap-right-small").text();
        String head = doc.select("#1 > div.c-border.c-gap-bottom-small > div:nth-child(1)").text();
        String heads[] = head.split(" ");
        String j = heads[0];
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(j);
        String code = "20"+m.replaceAll("").trim();
        String date = heads[1].split("：")[1];
        String week = getWeek(date);
        String content = doc.select("#1 > div.c-border.c-gap-bottom-small > div:nth-child(3)").text();
        log.info(content);
        log.info("星期："+week);
        log.info("开奖日期："+date);
        log.info("code:"+ code);
        log.info(red1);
        log.info(red2);
        log.info(red3);
        log.info(red4);
        log.info(red5);
        log.info(red6);
        log.info(bule);
        String red = red1+","+red2+","+red3+","+red4+","+red5+","+red6;
        DoubleBall doubleBall = new DoubleBall();
        doubleBall.setName("双色球");
        doubleBall.setCode(code);
        doubleBall.setRed(red);
        doubleBall.setBlue(bule);
        doubleBall.setWeek(week);
        doubleBall.setDate(date);
        doubleBall.setContent(content);

        if(doubleBallRepository.findByCode(code).size()!=0){
            log.info("数据库中已经存在"+code+"期数据！");
        }else {
            doubleBallJpaRepository.save(doubleBall);
            log.info(code+"期数据保存成功！");
        }
    }


    /**
     * 全量数据获取
     * @param url
     * @throws IOException
     * @throws InterruptedException
     */
    public void doubleBallCrawler(String url) throws Exception {
        for(int i=1;i<20;i++){
            String myUrl = url;
            log.info("第"+i+"页");
            myUrl+=i;
            log.info(myUrl);
            String cookies = "Sites=_21; UniqueID=u8iZxBi585J3agOZ1515637240923; Hm_lvt_bddcb154f385b71c72d95dae58113e9c=1513913346; 21_vq=27";
            String userAgent="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
            Map<String,String> header = new HashMap<>();
            header.put("Cookie",cookies);
            header.put("user-agent",userAgent);
            header.put("accept","application/json, text/javascript, */*; q=0.01");
            header.put("connection","Keep-Alive");
            header.put("Referer","http://www.cwl.gov.cn/kjxx/ssq/kjgg/");
            header.put("host","www.cwl.gov.cn");
            String content = HttpRequest.sendGet(myUrl,header);
            log.info(content);
            log.info("开始解析 第"+i+"页数据...");
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray datas = jsonObject.getJSONArray("result");
            String[] dataArr = JsoupHelper.getJsonToStringArray(datas);
            for(String data:dataArr){
                JSONObject dataObj = JSON.parseObject(data);
                DoubleBall doubleBall = new DoubleBall();
                String name = dataObj.getString("name");
                String code = dataObj.getString("code");
                String detailsLink = dataObj.getString("detailsLink");
                String videoLink = dataObj.getString("videoLink");
                String date = dataObj.getString("date");
                String week = dataObj.getString("week");
                String red = dataObj.getString("red");
                String blue = dataObj.getString("blue");
                String blue2 = dataObj.getString("blue2");
                String sales = dataObj.getString("sales");
                String poolmoney = dataObj.getString("poolmoney");
                String content1 = dataObj.getString("content");
                String addmoney = dataObj.getString("addmoney");
                String addmoney2 = dataObj.getString("addmoney2");
                String msg = dataObj.getString("msg");
                String z2add = dataObj.getString("z2add");
                String m2add = dataObj.getString("m2add");
                doubleBall.setName(name);
                doubleBall.setCode(code);
                doubleBall.setDetailsLink(detailsLink);
                doubleBall.setVideoLink(videoLink);
                doubleBall.setDate(date);
                doubleBall.setWeek(week);
                doubleBall.setRed(red);
                doubleBall.setBlue(blue);
                doubleBall.setBlue2(blue2);
                doubleBall.setSales(Long.parseLong(sales));
                doubleBall.setPoolmoney(Long.parseLong(poolmoney));
                doubleBall.setContent(content1);
                doubleBall.setAddmoney(addmoney);
                doubleBall.setAddmoney2(addmoney2);
                doubleBall.setMsg(msg);
                doubleBall.setM2add(m2add);
                doubleBall.setZ2add(z2add);
                doubleBallJpaRepository.save(doubleBall);
            }
            Thread.sleep(10000);
            log.info("第"+i+"页数据解析成功...");
        }

    }

    /**
     * 随机一注，排除往期中奖
     * @return
     */
    public Map<String,Object> randomDoubleBall(){
        // 初始化 红蓝 球
        List<Integer> reds = new ArrayList<>();
        for(int i=1;i<=33;i++){
            reds.add(i);
        }
        // 生成篮球
        int blue = (int)(Math.random()*16 + 1);
        // 生成红球
        int red1 = reds.remove((int)(Math.random()*33));
        int red2 = reds.remove((int)(Math.random()*32));
        int red3 = reds.remove((int)(Math.random()*31));
        int red4 = reds.remove((int)(Math.random()*30));
        int red5 = reds.remove((int)(Math.random()*29));
        int red6 = reds.remove((int)(Math.random()*28));
        log.info("红1："+red1);
        log.info("红2："+red2);
        log.info("红3："+red3);
        log.info("红4："+red4);
        log.info("红5："+red5);
        log.info("红6："+red6);
        log.info("蓝："+blue);

        Integer [] redres = {red1,red2,red3,red4,red5,red6};
        Arrays.sort(redres);
        // 判断是否与往期开奖记录重复？
        boolean isRepeat = isRepeat(redres);
        if(isRepeat){
            log.info("警告：出现与历史数据相同的号码！");
            String temp = "";
            for(int i:redres){
                temp=(i< 10) ? reds+"0"+i+"," : temp+i+"," ;
            }
            log.info("重复数据："+temp.substring(0, temp.length() - 1));
            randomDoubleBall();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("red",Arrays.asList(redres));
        map.put("blue",blue);
        return map;
    }

    /**
     * 判断是否与历史中奖纪录重复
     * @param arrs
     * @return
     */
    private boolean isRepeat(Integer[] arrs){
        String reds = "";
        for(int i:arrs){
            reds = (i< 10) ? reds+"0"+i+"," : reds+i+"," ;
        }
        reds = reds.substring(0, reds.length() - 1);
        // 去开奖历史中查询是否出现过
        return  doubleBallRepository.findByRed(reds).size()>1 ? true:false;
    }

    /**
     * 查询给定日期所属星期几
     * @param date
     * @return
     */
    private String getWeek(String date){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(date);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static void main(String[] args) throws Exception {
        String url = "http://www.cwl.gov.cn/cwl_admin/kjxx/findDrawNotice?name=ssq&issueCount=&issueStart=&issueEnd=&dayStart=2012-10-24&dayEnd=2018-01-11&pageNo=";
        WelfareLotteryService welfareLotteryService = new WelfareLotteryService();
//        welfareLotteryService.doubleBallCrawler(url);
        welfareLotteryService.incrementCrawlerDoubleBallData();
    }
}
