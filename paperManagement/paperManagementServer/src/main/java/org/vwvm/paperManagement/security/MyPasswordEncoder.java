package org.vwvm.paperManagement.security;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <h3>store</h3>
 * <p>自定义密码校验器</p>
 *
 * @author : BlackBox
 * @date : 2023-04-13 22:10
 **/
public class MyPasswordEncoder implements PasswordEncoder {

    private static final PasswordEncoder INSTANCE = new MyPasswordEncoder();

    /**
     * Encode the raw password. Generally, a good encoding algorithm applies a SHA-1 or
     * greater hash combined with an 8-byte or greater randomly generated salt.
     *
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * Verify the encoded password obtained from storage matches the submitted raw
     * password after it too is encoded. Returns true if the passwords match, false if
     * they do not. The stored password itself is never decoded.
     *
     * @param rawPassword     the raw password to encode and match
     * @param encodedPassword the encoded password from storage to compare with
     * @return true if the raw password, after encoding, matches the encoded password from
     * storage
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    /**
     * Get the singleton {@link MyPasswordEncoder}.
     */
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }
}
