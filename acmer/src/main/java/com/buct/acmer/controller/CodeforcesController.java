package com.buct.acmer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.service.impl.CodeforcesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Api(tags = "Codeforces")
@RestController
@RequestMapping("/acmer/codeforces")
public class CodeforcesController {

    @Resource
    private CodeforcesServiceImpl codeforcesService;

    @ApiOperation("查询全部学生Codeforces信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页数",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",required = true)
    })
    @GetMapping("/all/{currentPage}/{pageSize}")
    public PublicProperty<Page<Codeforces>> selectAll(@PathVariable("currentPage") Integer currentPage,
                                                      @PathVariable("pageSize") Integer pageSize){

        Page<Codeforces> page = new Page<>(currentPage,pageSize);
        return new PublicProperty(200,"success",codeforcesService.page(page));
    }

}
