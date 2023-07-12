package com.buct.acmer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.entity.Student;
import com.buct.acmer.service.impl.CodeforcesServiceImpl;
import com.sun.org.apache.bcel.internal.classfile.Code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("根据id删除Codeforces信息")
    @ApiImplicitParam(name = "cfId", value = "cfid", required = true)
    @DeleteMapping ("/remove/id/{cfId}")
    public PublicProperty delete(@PathVariable("cfId") String cfId) {
        boolean flag = codeforcesService.removeById(cfId);
        if (flag) {
            return new PublicProperty(200, "success", null);
        } else {
            return new PublicProperty(400, "failed", null);
        }
    }

    @ApiOperation("修改学生Codeforces信息")
    @PostMapping("/update")
    public PublicProperty<Codeforces> updateInfo(@RequestBody Codeforces codeforces) {

        Codeforces exist = codeforcesService.getById(codeforces.getCfId());
        if (exist != null) {
            if (codeforces.getCfContest() != "" && codeforces.getCfContest() != null) {
                exist.setCfContest(codeforces.getCfContest());
            }
            if (codeforces.getCfContestId() != "" && codeforces.getCfContestId() != null) {
                exist.setCfContestId(codeforces.getCfContestId());
            }
            if (codeforces.getCfRank() != "" && codeforces.getCfRank() != null) {
                exist.setCfRank(codeforces.getCfRank());
            }
            if (codeforces.getCfNewRating() != "" && codeforces.getCfNewRating() != null) {
                exist.setCfOldRating(codeforces.getCfOldRating());
            }
            if (codeforces.getCfNewRating() != "" && codeforces.getCfNewRating() != null) {
                exist.setCfNewRating(codeforces.getCfNewRating());
            }
            if (codeforces.getCfSumContest() != "" && codeforces.getCfSumContest() != null) {
                exist.setCfSumContest(codeforces.getCfSumContest());
            }
            if (codeforces.getCfDate() != "" && codeforces.getCfDate() != null) {
                exist.setCfDate(codeforces.getCfDate());
            }
            boolean update = codeforcesService.updateById(exist);
            if (update) {
                return new PublicProperty(200, "success", codeforces);
            } else {
                return new PublicProperty(400, "failed", null);
            }
        } else {
            return new PublicProperty(400, "user does not exist", null);
        }

    }
}
