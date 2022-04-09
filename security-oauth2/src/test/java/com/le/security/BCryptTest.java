package com.le.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @Auther: xll
 * @Date: 2022/1/16 15:52
 */
@SpringBootTest(classes = {SecurityApplication.class})

public class BCryptTest {

    @Test
    public void testBCrypt() {
        // encode
        String cipher = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(cipher);

        // decode
        boolean flag = BCrypt.checkpw("123", "$2a$10$.ac2w1RbHJ19tiPsOYrh3uYkFrO0nhwgA4zxjkKzlpVDZkEKMMQP.");
        Assert.assertTrue(flag);
    }
}
