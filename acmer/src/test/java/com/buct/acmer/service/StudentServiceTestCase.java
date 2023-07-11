package com.buct.acmer.service;

import com.buct.acmer.entity.Student;
import com.buct.acmer.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTestCase {

    @Autowired
    private IStudentService studentService;

    @Test
    void testGet(){
        System.out.println(studentService.getById(2));
    }

    @Test
    void deleteByName(){
        String name = "田帅华";
        System.out.println(studentService.removeByName(name));
    }
}
