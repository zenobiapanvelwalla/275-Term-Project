package com.backend.netflix.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.backend.netflix.repository.UserRepository;
import com.backend.netflix.vo.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        User alex = new User(0, "alex@gmail.com", null, "Alex", false, null, null);
        entityManager.persist(alex);
        entityManager.flush();
     
        // when
        User found = userRepository.findByDisplayName(alex.getDisplayName()).get();
        
        System.out.println("DISPLAY NAME IS: "+found.getDisplayName());
     
        // then
        assertThat(found.getDisplayName())
          .isEqualTo(alex.getDisplayName());
    }

}
