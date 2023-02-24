package org.vwvm.areamanagementfunction.adminServer.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.areamanagementfunction.adminServer.AdminServerApplication;
import org.vwvm.areamanagementfunction.adminServer.commons.utils.JwtUtils;
import org.vwvm.areamanagementfunction.adminServer.entity.User;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AdminServerApplication.class)
class UserMapperTest {

    private UserMapper userMapper;

    @Resource
    public void setUserMapperTest(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Test
    void testSelectList(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void testJwt(){
        System.out.println(JwtUtils.createJwt(Map.of("admin", "jjjj")));
        System.out.println(JwtUtils.getPayLoad("eyJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6ImpqamoiLCJpYXQiOjE2NzcwNDQ0NzgsImV4cCI6MTY3NzEzMDg3OH0.RE68Xf9sTk8bCPrbWDZUM1iV0qknCX2jtmdDl_3LNTE"));
    }

    @Test
    void textMax(){
        QueryWrapper queryWrapper = new QueryWrapper<User>();



    }


}