package org.dl.debbi.user.account.web.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Ignore
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
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/user/login")).andReturn();
    }
}
