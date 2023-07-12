package com.buct.acmer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.entity.ContestInfo;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.service.impl.AtcoderServiceImpl;
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
 * @since 2022-06-14
 */
@Api(tags = "AtCoder")
@RestController
@RequestMapping("/acmer/atcoder")
public class AtcoderController {

    @Resource
    private AtcoderServiceImpl atcoderService;

    @ApiOperation("查询全部学生AtCoder信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页数",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",required = true)
    })
    @GetMapping("/all/{currentPage}/{pageSize}")
    public PublicProperty<Page<Atcoder>> selectAll(@PathVariable("currentPage") Integer currentPage,
                                                   @PathVariable("pageSize") Integer pageSize){

        Page<Atcoder> page = new Page<>(currentPage,pageSize);
        return new PublicProperty(200,"success",atcoderService.page(page));
    }

    @ApiOperation("修改学生Atcoder信息")
    @PostMapping("/update")
    public PublicProperty<Codeforces> updateInfo(@RequestBody Atcoder atcoder) {

        Atcoder exist = atcoderService.getById(atcoder.getAcId());
        if (exist != null) {
            if (atcoder.getAcDate() != "" && atcoder.getAcDate() != null) {
                exist.setAcDate(atcoder.getAcDate());
            }
            if (atcoder.getAcContest() != "" && atcoder.getAcContest() != null) {
                exist.setAcContest(atcoder.getAcContest());
            }
            if (atcoder.getAcRank() != "" && atcoder.getAcRank() != null) {
                exist.setAcRank(atcoder.getAcRank());
            }
            if (atcoder.getAcPerformance() != "" && atcoder.getAcPerformance() != null) {
                exist.setAcPerformance(atcoder.getAcPerformance());
            }
            if (atcoder.getAcNewrating() != "" && atcoder.getAcNewrating() != null) {
                exist.setAcNewrating(atcoder.getAcNewrating());
            }
            if (atcoder.getAcDiff() != "" && atcoder.getAcDiff() != null) {
                exist.setAcDiff(atcoder.getAcDiff());
            }
            if (atcoder.getAcCount() != "" && atcoder.getAcCount() != null) {
                exist.setAcCount(atcoder.getAcCount());
            }
            if (atcoder.getAcMaxrating() != "" && atcoder.getAcMaxrating() != null) {
                exist.setAcMaxrating(atcoder.getAcMaxrating());
            }
            boolean update = atcoderService.updateById(exist);
            if (update) {
                return new PublicProperty(200, "success", atcoder);
            } else {
                return new PublicProperty(400, "failed", null);
            }
        } else {
            return new PublicProperty(400, "user does not exist", null);
        }

    }

}
