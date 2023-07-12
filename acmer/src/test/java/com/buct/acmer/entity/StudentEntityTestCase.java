package com.buct.acmer.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.mapper.StudentMapper;
import org.apache.ibatis.annotations.Delete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentEntityTestCase {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void testSave() {
        studentMapper.selectById(1);
    }

    @Test
    void testDelete() {
        String name = "田帅华";
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("stu_name",name);
        if(!studentMapper.selectList(qw).isEmpty()){
            studentMapper.deleteByName(name);
        }
    }

    @Test
        //测试分页功能
    void testGetPage() {
        IPage page = new Page(2, 5);
        studentMapper.selectPage(page, null);
    }

    @Test
        //按条件查询
    void testGetBy() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("stu_name", "田帅华");
        studentMapper.selectList(qw);

    }

    @Test
    void testGetBy2() {
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
        lqw.like(Student::getStuClass, "20");
        studentMapper.selectList(lqw);
    }
}
