package com.buct.acmer.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.mapper.CodeforcesMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Api(value = " Codeforces比赛数据", tags = " Codeforces比赛数据")
@RestController
@RequestMapping("/api/codeforces")
public class CodeforcesController {

    @Autowired
    private CodeforcesMapper codeforcesMapper;

    /**
     * 分页查询
     */
    @GetMapping("/list")
    @ApiOperation(value = " Codeforces比赛数据分页", notes = " Codeforces比赛数据分页")
    public Page<Codeforces> list(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int limit,
                                 @RequestParam(required = false) String type,
                                 @RequestParam(required = false) String name) {
        Page<Codeforces> iPage = new Page<>(page, limit);
        QueryWrapper<Codeforces> wrapper = new QueryWrapper<>();
        if (type != null && !type.isEmpty()) {
            wrapper.eq("type", type);
        }
        if (name != null && !name.isEmpty()) {
            wrapper.like("name", name);
        }

        return codeforcesMapper.selectPage(iPage, wrapper);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperation(value = " Codeforces比赛数据新增", notes = " Codeforces比赛数据新增")
    public void add(@RequestBody Codeforces codeforces) {
        Codeforces selectOne = codeforcesMapper.selectOne(new LambdaQueryWrapper<Codeforces>().eq(Codeforces::getCid, codeforces.getCid()));
        if (selectOne == null) {
            codeforcesMapper.insert(codeforces);
        } else {
            codeforcesMapper.update(codeforces, new LambdaQueryWrapper<Codeforces>().eq(Codeforces::getCid, codeforces.getCid()));
        }

    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = " Codeforces比赛数据修改", notes = " Codeforces比赛数据修改")
    public void update(@RequestBody Codeforces codeforces) {
        codeforcesMapper.updateById(codeforces);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = " Codeforces比赛数据删除", notes = " Codeforces比赛数据删除")
    public void delete(@RequestParam int cid) {
        codeforcesMapper.deleteById(cid);
    }

    /**
     * 详情
     */
    @PostMapping("/get")
    @ApiOperation(value = " Codeforces比赛数据详情", notes = " Codeforces比赛数据详情")
    public void get(@RequestParam int cid) {
        codeforcesMapper.selectById(cid);
    }


}
