package com.buct.acmer.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.mapper.CodeforcesMapper;
import io.swagger.annotations.Api;
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
@Api(tags = "Codeforces")
@RestController
@RequestMapping("/api/codeforces")
public class CodeforcesController {

    @Autowired
    private CodeforcesMapper codeforcesMapper;

    /**
     * 分页查询
     */
    @GetMapping("/list")
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
    public void update(@RequestBody Codeforces codeforces) {
        codeforcesMapper.updateById(codeforces);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public void delete(@RequestParam int cid) {
        codeforcesMapper.deleteById(cid);
    }


}
