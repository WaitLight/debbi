package org.dl.debbi.user.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 用户控制器测试
 *
 * @author Dean
 * @version 0.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(controllers = {UserController.class})
public class UserControllerTest {

    @MockBean
    private MockMvc mockMvc;

    /**
     * 登录测试
     */
    @Test
    public void loginTest() throws Exception {
        //TODO 各种情况的模拟
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/login")).andReturn();

    }
}
