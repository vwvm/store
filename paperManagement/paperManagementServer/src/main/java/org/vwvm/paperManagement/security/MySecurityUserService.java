package org.vwvm.paperManagement.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.vwvm.paperManagement.entity.User;
import org.vwvm.paperManagement.mapper.UserMapper;

import java.util.Objects;

/**
 * <h3>store</h3>
 * <p>自定义security用户查询</p>
 *
 * @author : BlackBox
 * @date : 2023-04-13 10:29
 **/
@Component
public class MySecurityUserService implements UserDetailsService, UserDetailsPasswordService {

    private final UserMapper userMapper;

    @Autowired
    public MySecurityUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_username", username);
        userQueryWrapper.last("limit 1");   //保证只返回1个结果
        User user = userMapper.selectOne(userQueryWrapper);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }

    /**
     * Modify the specified user's password. This should change the user's password in the
     * persistent user repository (database, LDAP etc).
     *
     * @param user        the user to modify the password for
     * @param newPassword the password to change to, encoded by the configured
     *                    {@code PasswordEncoder}
     * @return the updated UserDetails with the new password
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        if (user instanceof User user1){
            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.set("user_password", newPassword);
            userUpdateWrapper.eq("id", user1.getId());
            int update = userMapper.update((User) user, userUpdateWrapper);
            if (update > 0){
                user1.setUserPassword(newPassword);
            }
        }

        return user;
    }
}
