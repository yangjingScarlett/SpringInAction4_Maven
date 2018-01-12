package controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spittr.controller.HomeController;

/**
 * Created by yangjing on 2018/1/9
 */
public class HomeControllerTest {

    @Test
    public void testHomePage() throws Exception {
        HomeController homeController = new HomeController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();//构建MockMvc实例
        mockMvc.perform(MockMvcRequestBuilders.get("/"))//对"/"执行get请求
                .andExpect(MockMvcResultMatchers.view().name("home"));//预期得到视图名称
    }

}
