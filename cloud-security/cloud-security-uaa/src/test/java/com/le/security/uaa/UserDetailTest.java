package com.le.security.uaa;

import com.le.security.uaa.dao.UserDao;
import com.le.security.uaa.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: xll
 * @Date: 2022/1/18 10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testLoadUser() {
        UserDTO zhangsan = userDao.queryUserByUsername("zhangsan");
        System.out.println(zhangsan);
    }
}
