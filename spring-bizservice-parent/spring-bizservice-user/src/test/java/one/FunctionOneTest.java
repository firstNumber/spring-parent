package one;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.bizservice.user.UserBizServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author liyongzhen
 * @create 2018-05-14 13:21
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= UserBizServiceApplication.class)
public class FunctionOneTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testOne() {
         ObjectMapper mapper = new ObjectMapper();
        String responseString = null;
        try {
            responseString = mvc.perform(get("/user/getUserInfo/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(mapper.writeValueAsString(110)).accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(responseString);


    }
}
