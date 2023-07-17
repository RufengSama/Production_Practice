package com.buct.acmer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Student;
import com.buct.acmer.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    // 查询所有学生（支持分页）
    @GetMapping("/page")
    public Page<Student> getAllStudents(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize,
                                        @RequestParam(required = false) String realName,
                                        @RequestParam(required = false) String className,
                                        @RequestParam(required = false) String atcoderId,
                                        @RequestParam(required = false) String codeforcesId,
                                        @RequestParam(required = false) Integer year,
                                        @RequestParam(required = false) Integer schoolName) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(realName)) {
            queryWrapper.like("real_name", realName);
        }
        if (StringUtils.isNotBlank(className)) {
            queryWrapper.like("class_name", className);
        }
        if (StringUtils.isNotBlank(atcoderId)) {
            queryWrapper.like("atcoder_id", atcoderId);
        }
        if (StringUtils.isNotBlank(codeforcesId)) {
            queryWrapper.like("codeforces_id", codeforcesId);
        }
        if (year != null) {
            queryWrapper.eq("year", year);
        }
        if (schoolName != null) {
            queryWrapper.eq("school_name", schoolName);
        }
        return studentService.getAllStudents(pageNo, pageSize, queryWrapper);
    }

    // 查询指定学生
    @GetMapping("/{stuNo}")
    public Student getStudent(@PathVariable String stuNo) {
        return studentService.getStudent(stuNo);
    }

    // 新增学生
    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    // 修改学生信息
    @PutMapping("/{stuNo}")
    public void updateStudent(@PathVariable String stuNo, @RequestBody Student student) {
        student.setStuNo(stuNo);
        studentService.updateStudent(student);
    }

    // 删除学生
    @DeleteMapping("/{stuNo}")
    public void deleteStudent(@PathVariable String stuNo) {
        studentService.deleteStudent(stuNo);
    }
}