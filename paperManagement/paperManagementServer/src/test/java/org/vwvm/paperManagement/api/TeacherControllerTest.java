package org.vwvm.paperManagement.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;
import org.vwvm.paperManagement.commons.vo.ResultsVO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class TeacherControllerTest {

    @Autowired
    TeacherController teacherController;

    @Test
    void getTeacherList(){
        ResultsVO teacherList = teacherController.getTeacherList(1);
        System.out.println(teacherList.getData());
    }
}
