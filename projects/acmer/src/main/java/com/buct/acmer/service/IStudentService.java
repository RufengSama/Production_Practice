package com.buct.acmer.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
public interface IStudentService extends IService<Student> {

    Page<Student> getAllStudents(int pageNo, int pageSize, QueryWrapper<Student> queryWrapper);

    Student getStudent(String stuNo);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(String stuNo);
}
