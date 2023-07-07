package com.buct.acmer.service;

import com.buct.acmer.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTestCase {

    @Autowired
    private IStudentService studentService;
}
