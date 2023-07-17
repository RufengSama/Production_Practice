package com.buct.acmer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Student;
import com.buct.acmer.mapper.StudentMapper;
import com.buct.acmer.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Page<Student> getAllStudents(int pageNo, int pageSize, QueryWrapper<Student> queryWrapper) {
        return baseMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }

    @Override
    public Student getStudent(String stuNo) {
        return baseMapper.selectOne(new LambdaQueryWrapper<Student>().eq(Student::getStuNo, stuNo));
    }

    @Override
    public void addStudent(Student student) {
        baseMapper.insert(student);
    }

    @Override
    public void updateStudent(Student student) {
        baseMapper.updateById(student);
    }

    @Override
    public void deleteStudent(String stuNo) {
        baseMapper.delete(new LambdaQueryWrapper<Student>().eq(Student::getStuNo, stuNo));
    }
}
