package com.buct.acmer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.entity.ContestInfo;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.entity.Student;
import com.buct.acmer.service.impl.StudentServiceImpl;
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
@Api(tags = "学生信息")
@RestController
@RequestMapping("/acmer/student")
public class StudentController {

    @Resource
    private StudentServiceImpl studentService;

    @ApiOperation("查询学生信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页数",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",required = true)
    })
    @GetMapping("/all/{currentPage}/{pageSize}")
    public PublicProperty<Page<Student>> selectAll(@PathVariable("currentPage") Integer currentPage,
                                                   @PathVariable("pageSize") Integer pageSize){

        Page<Student> page = new Page<>(currentPage,pageSize);
        return new PublicProperty(200,"success",studentService.page(page));
    }

    @ApiOperation("新增学生信息")
    @PostMapping("/insert")
    public PublicProperty insert(@RequestBody Student student){

        Student exist = studentService.getById(student.getStuNo());
        if(exist == null){
            boolean save = studentService.save(student);
            if(save){
                return new PublicProperty(200,"success",null);
            }else{
                return new PublicProperty(400,"failed",null);
            }
        }else {
            return new PublicProperty(400,"student exist",null);
        }
    }

    @ApiOperation("根据id删除学生信息")
    @ApiImplicitParam(name = "stuId",value = "学生id",required = true)
    @GetMapping("/remove/{stuId}")
    public PublicProperty delete(@PathVariable("stuId") String stuId){

        boolean flag = studentService.removeById(stuId);
        if(flag){
            return new PublicProperty(200,"success",null);
        }else {
            return new PublicProperty(400,"failed",null);
        }
    }

    @ApiOperation("修改学生竞赛信息")
    @PostMapping("/update")
    public PublicProperty<Student> updateInfo(@RequestBody Student student){

        Student exist = studentService.getById(student.getStuNo());
        if(exist!=null){
            if(student.getStuName()!="" && student.getStuName() !=null ){
                exist.setStuName(student.getStuName());
            }
            if(student.getStuClass()!="" && student.getStuClass() !=null ){
                exist.setStuClass(student.getStuClass());
            }
            if(student.getStuAcId()!="" && student.getStuAcId() !=null ){
                exist.setStuAcId(student.getStuAcId());
            }
            if(student.getStuCfId()!="" && student.getStuCfId() !=null ){
                exist.setStuCfId(student.getStuCfId());
            }
            boolean update = studentService.updateById(exist);
            if(update){
                return new PublicProperty(200,"success",student);
            }else{
                return new PublicProperty(400,"failed",null);
            }
        }else {
            return new PublicProperty(400,"user does not exist",null);
        }

    }

}
