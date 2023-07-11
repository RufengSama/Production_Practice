package com.buct.spider.codeforces;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buct.spider.entity.Atcoder;
import com.buct.spider.entity.Codeforces;
import com.buct.spider.entity.Student;
import com.buct.spider.mapper.CodeforcesMapper;
import com.buct.spider.mapper.StudentMapper;
import com.buct.spider.util.HttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class GetCfData {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CodeforcesMapper codeforcesMapper;

    /**
     * 获取cf比赛数据，cf数据为json格式返回，不需要解析html页面
     */
    public void getInfo(){
        List<Student> students = studentMapper.selectList(null);
        for (int i = 0; i < students.size(); i++) {
            String id = students.get(i).getStuCfId();
            if(id!=null && !id.equals("")){
                //获取json数据
                String data = HttpRequest.sendGet("https://codeforces.com/api/user.rating?handle=" + id);
                JSONObject jsonObject = JSONObject.parseObject(data);
                JSONArray result = jsonObject.getJSONArray("result");
                if(result.size()!=0){
                    JSONObject newOne = result.getJSONObject(result.size()-1);
                    //封装成codeforces对象
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Codeforces codeforces = new Codeforces();
                    codeforces.setCfId(newOne.getString("handle"));
                    codeforces.setCfContestId(newOne.getString("contestId"));
                    String ratingUpdateTimeSeconds = simpleDateFormat.format(new Date(newOne.getLong("ratingUpdateTimeSeconds")*1000));
                    codeforces.setCfDate(ratingUpdateTimeSeconds);
                    codeforces.setCfNewRating(newOne.getString("newRating"));
                    codeforces.setCfRank(newOne.getString("rank"));
                    codeforces.setCfContest(newOne.getString("contestName"));
                    codeforces.setCfSumContest(String.valueOf(result.size()));
                    codeforces.setCfOldRating(newOne.getString("oldRating"));
                    //存入数据库中
                    QueryWrapper<Codeforces> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("cf_id",codeforces.getCfId());
                    Codeforces exist = codeforcesMapper.selectById(codeforces.getCfId());
                    if(exist==null){
                        codeforcesMapper.insert(codeforces);
                    }else {
                        codeforcesMapper.update(codeforces,queryWrapper);
                    }
                }
            }
        }
    }
}
