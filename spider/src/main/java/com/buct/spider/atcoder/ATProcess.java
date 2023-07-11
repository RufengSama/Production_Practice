package com.buct.spider.atcoder;

import com.buct.spider.entity.Atcoder;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

@Component
public class ATProcess implements PageProcessor {

    private static final Site SITE = Site.me()
            // 设置字符编码集
            .setCharset("UTF-8")
            // 设置Http连接重试次数
            .setRetryTimes(30)
            // 设置线程休眠时间
            .setSleepTime(1000);

    /**
     * 页面分析
     * @param page 下载结果封装成Page对象
     */
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        //解析页面内容
        Selectable table = html.css("#history");
        List<String> tr = table.css("tbody > tr").all();
        String date = table.css("tbody > tr:nth-child("+tr.size()+") > td:nth-child(1) > time","text").get();
        String contest = table.css("tbody > tr:nth-child("+tr.size()+") > td:nth-child(2) > a","text").get();
        String rank = table.css("tbody > tr:nth-child("+tr.size()+") > td:nth-child(3) > a","text").get();
        String performance = table.css("tbody > tr:nth-child("+tr.size()+") > td:nth-child(4)","text").get();
        String newRating = table.css("tbody > tr:nth-child("+tr.size()+") > td:nth-child(5) > span","text").get();
        String diff = table.css("tbody > tr:nth-child("+tr.size()+") > td:nth-child(6)","text").get();

        //查找历史最高积分
        Integer maxRating = 0;
        for (int i = 1; i <= tr.size(); i++) {
            String temp = table.css("tbody > tr:nth-child("+i+") > td:nth-child(5) > span","text").get();
            if(temp!=null && Integer.parseInt(temp)>maxRating){
                maxRating = Integer.parseInt(temp);
            }
        }

        Selectable url = page.getUrl();
        String[] split = url.toString().split("/");

        Atcoder atcoder = new Atcoder();
        atcoder.setAcId(split[split.length-2]);
        atcoder.setAcDate(date);
        atcoder.setAcContest(contest);
        atcoder.setAcRank(rank);
        atcoder.setAcPerformance(performance);
        atcoder.setAcNewrating(newRating==null?"-":newRating);
        atcoder.setAcDiff(diff);
        atcoder.setAcCount(String.valueOf(tr.size()));
        atcoder.setAcMaxrating(String.valueOf(maxRating));

        //把结果传递给pipeline
        page.putField("data",atcoder);
    }

    /**
     * 返回site对象
     * site是站点配置 使用Site，me()创建site对象
     * @return
     */
    @Override
    public Site getSite() {
        return SITE;
    }

    public static void main(String[] args) {
        int a= 1000*60*60*24*7;
        System.out.println(a);
    }
}
