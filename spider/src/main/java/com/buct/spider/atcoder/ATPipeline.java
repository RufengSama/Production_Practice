package com.buct.spider.atcoder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buct.spider.entity.Atcoder;
import com.buct.spider.mapper.AtcoderMapper;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

@Component
public class ATPipeline implements Pipeline {

    @Resource
    private AtcoderMapper atcoderMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {

        Atcoder atcoder = resultItems.get("data");
        QueryWrapper<Atcoder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ac_id",atcoder.getAcId());
        Atcoder exist = atcoderMapper.selectById(atcoder.getAcId());
        if(exist==null){
            atcoderMapper.insert(atcoder);
        }else {
            atcoderMapper.update(atcoder,queryWrapper);
        }

    }
}
