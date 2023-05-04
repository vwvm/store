package org.vwvm.paperManagement.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.entity.Project;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class ProjectServiceImplTest {

    @Autowired
    ProjectServiceImpl projectService;


    @Test
    void testProjectService(){
        ResultsVO projectServiceList = projectService.getProjectServiceList(1, 100, 2, 2, "");
        Object data = projectServiceList.getData();
        if (data instanceof List<?> project){
            project.forEach(p ->{
                if (p instanceof Project project1){
                    System.out.println(project1);
                }
            });
        }

    }

    @Test
    void test(){
        List<String> of = List.of("1", "2", "3");
        List<String> collect = of.stream().map(s -> s + s).toList();
        System.out.println(collect);

    }
}
