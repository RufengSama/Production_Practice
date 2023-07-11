package com.buct.acmer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public boolean removeByName(String stuName) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        qw.like("stu_name", stuName);
        if (!studentMapper.selectList(qw).isEmpty()) {
            studentMapper.deleteByName(stuName);
            return true;
        } else return false;
    }
}
