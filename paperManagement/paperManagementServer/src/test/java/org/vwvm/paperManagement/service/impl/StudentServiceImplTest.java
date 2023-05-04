package org.vwvm.paperManagement.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {

    @Autowired
    StudentServiceImpl studentService;


    @Test
    void getStudent() {
        System.out.println(studentService.getStudent(21).getData());
    }
}
