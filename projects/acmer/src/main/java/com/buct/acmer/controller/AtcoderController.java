package com.buct.acmer.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.mapper.AtcoderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atcoder")
public class AtcoderController {

    @Autowired
    private AtcoderMapper atcoderMapper;

    /**
     * 分页查询
     */
    @GetMapping("/list")
    public Page<Atcoder> list(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int limit,
                              @RequestParam(required = false) String type,
                              @RequestParam(required = false) String name) {
        Page<Atcoder> iPage = new Page<>(page, limit);
        QueryWrapper<Atcoder> wrapper = new QueryWrapper<>();
        if (type != null && !type.isEmpty()) {
            wrapper.eq("type", type);
        }
        if (name != null && !name.isEmpty()) {
            wrapper.like("name", name);
        }
        wrapper.orderByAsc("id");
        return atcoderMapper.selectPage(iPage, wrapper);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public void add(@RequestBody Atcoder atcoder) {
        Atcoder selectOne = atcoderMapper.selectOne(new LambdaQueryWrapper<Atcoder>().eq(Atcoder::getId, atcoder.getId()));
        if (selectOne == null) {
            atcoderMapper.insert(atcoder);
        } else {
            atcoderMapper.update(atcoder, new LambdaQueryWrapper<Atcoder>().eq(Atcoder::getId, atcoder.getId()));
        }

    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public void update(@RequestBody Atcoder atcoder) {
        atcoderMapper.updateById(atcoder);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public void delete(@RequestParam int cid) {
        atcoderMapper.deleteById(cid);
    }


}