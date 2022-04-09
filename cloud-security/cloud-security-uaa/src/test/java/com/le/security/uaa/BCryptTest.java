package com.le.security.uaa;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @Auther: xll
 * @Date: 2022/1/19 8:55
 */
@SpringBootTest
public class BCryptTest {
    @Test
    public void TestEncodeAndDecode() {
        String secret = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(secret);

        boolean b = BCrypt.checkpw("123", "$2a$10$CL4zugBXid5Aix198YH.xOu7wPwSQs1MNUCS7RtU25aP241webpiG");
        System.out.println(b);

    }
}
