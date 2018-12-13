package com.backend.netflix.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.backend.netflix.controllers.UserController;
import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.User;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerIntegrationTest {
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private UserService service;
 
    
    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray()
      throws Exception {
         
        User alex = new User(0, "alex@gmail.com", null, "Alex", false, null, null);
     
        List<User> allUsers = Arrays.asList(alex);
     
        given(service.getAllUsers()).willReturn(allUsers);
        
        
        mvc.perform(get("/users")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
          
    }

}
