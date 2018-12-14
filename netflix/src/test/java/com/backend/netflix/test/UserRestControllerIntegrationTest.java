package com.backend.netflix.test;

import com.backend.netflix.controllers.UserController;
import com.backend.netflix.services.BillingService;
import com.backend.netflix.services.MovieService;
import com.backend.netflix.services.UserService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerIntegrationTest {
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private UserService service;

    @MockBean
    UserSubscriptionService userSubscriptionService;

    @MockBean
    private MovieService movieService;

    @MockBean
    private BillingService billingService;

    
    @Test
    public void whenAdminFetchesAllUsers_AndUsersExist_allUsersAreReturned()
      throws Exception {
         
        User alex = new User(0, "alex@gmail.com", null, "Alex", false, null, null);
     
        List<User> allUsers = Arrays.asList(alex);
        given(service.getAllUsers()).willReturn(allUsers);
        mvc.perform(get("/users").sessionAttrs(sessionWithUserRoleAsAdmin())
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());

    }

    private Map<String,Object> sessionWithUserRoleAsAdmin() {
        Map<String, Object> sessionAtrributes = new HashMap<>();
        sessionAtrributes.put("role", "ADMIN");
        return sessionAtrributes;
    }

}
