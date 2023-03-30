package org.vwvm.store.api.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.vwvm.ApiApplication;
import org.vwvm.store.beans.entity.User;
import org.vwvm.store.mappers.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayDeque;

@SpringBootTest(classes = ApiApplication.class)
@ExtendWith(SpringExtension.class)
class MybatisPlusConfigTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 分页插件测试
     */
    @Test
    void pageTest() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        System.out.println(page);
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.hasPrevious());
        System.out.println(page.hasNext());
    }

    /**
     * 乐观锁测试
     */
    @Test
    void productTest() {
        User userA = userMapper.selectById(1);
        System.out.println("用户A获取年龄" + userA.getAge());
        User userB = userMapper.selectById(1);
        System.out.println("用户B获取年龄" + userB.getAge());
        //用户A操作年龄+20
        userA.setAge(userA.getAge() + 20);
        int updateByIdA = userMapper.updateById(userA);
        //用户A操作年龄-20
        userB.setAge(userB.getAge() - 20);
        int updateByIdB = userMapper.updateById(userB);
        System.out.println(updateByIdA);
        System.out.println(updateByIdB);

    }

    public static void main(String[] args) {
        System.out.println(Main(new int[]{10,1,2,3,4,5,6,1,2,3}));
    }

    static int Main(int[] nums) {

        var ans = 0;
        var st = new ArrayDeque<int[]>();
        for (var num : nums) {
            var maxT = 0;
            while (!st.isEmpty() && st.peek()[0] <= num)
                maxT = Math.max(maxT, st.pop()[1]);
            maxT = st.isEmpty() ? 0 : maxT + 1;
            ans = Math.max(ans, maxT);
            st.push(new int[]{num, maxT});
        }
        return ans;
    }

}
