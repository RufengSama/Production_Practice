package com.buct.spider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buct.spider.entity.Codeforces;
import com.buct.spider.mapper.CodeforcesMapper;
import com.buct.spider.service.ICodeforcesService;
import com.buct.spider.util.HttpUtil;
import com.buct.spider.util.TimeUtils;
import com.buct.spider.vo.CodeforcesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Service
public class CodeforcesServiceImpl extends ServiceImpl<CodeforcesMapper, Codeforces> implements ICodeforcesService {

    @Autowired
    private CodeforcesMapper codeforcesMapper;

    @Override
    public void codeforcesCrawling() {
        try {
            String result = HttpUtil.doGet("https://www.buctcoder.com/ACDC/api/codeforces/list");
            JSONObject json = JSONObject.parseObject(result);
            JSONArray data = json.getJSONArray("result");
            List<CodeforcesVO> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                CodeforcesVO codeforcesVO = JSON.toJavaObject(jsonObject, CodeforcesVO.class);
                list.add(codeforcesVO);
            }

            list.forEach(i -> {
                Codeforces selectOne = codeforcesMapper.selectOne(new LambdaQueryWrapper<Codeforces>()
                        .eq(Codeforces::getCid, i.getCid()));
                if (selectOne == null) {
                    Codeforces codeforces = new Codeforces();
                    codeforces.setCid(i.getCid())
                            .setName(i.getName())
                            .setType(i.getType())
                            .setParticipate(i.getParticipate())
                            .setStartTimeStamp(TimeUtils.timestampToString(
                                    Long.parseLong(String.valueOf(i.getStartTimeStamp()))*1000))
                            .setEndTimeStamp(TimeUtils.timestampToString( Long.parseLong(String.valueOf(i.getEndTimeStamp()))*1000));
                    codeforcesMapper.insert(codeforces);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
