package com.backend.netflix.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.backend.netflix.NetflixApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.netflix.repository.UserRepository;
import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NetflixApplication.class})
public class UserServiceImplIntegrationTest {
	
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService mockUserService() {
            return new UserService();
        }
    }
 
    @Autowired
    private UserService mockUserService;
 
    @MockBean
    private UserRepository userRepository;
 
    @Before
    public void setUp() {
        User alex = new User(0, "alex@gmail.com", null, "Alex", false, null, null);

        Mockito.when(userRepository.findByEmail(alex.getEmail()))
          .thenReturn(alex);
    }
    
    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = "alex@gmail.com";
        User found = mockUserService.findUserByEmail(email);
        System.out.println("FOUND EMAIL IS: "+found.getEmail());
         assertThat(found.getEmail())
          .isEqualTo(email);
     }

}
