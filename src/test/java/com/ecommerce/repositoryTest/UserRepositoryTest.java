package com.ecommerce.repositoryTest;


import com.ecommerce.user.UserRepo;

import com.ecommerce.user.User;
import com.ecommerce.role.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepo repo;


    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
       Role roleAdmin= entityManager.find(Role.class, 1);
        User na = new User("name@codejava.net", "nam2020", "Ha Minh", "Nam") ;

       na.addRole(null);
User savedUser=repo.save(na);
assertThat(savedUser.getId()).isGreaterThan(0);
        }

 @Test
    public void testListAllUsers(){
        Iterable<User> listUsers= repo.findAll();
        listUsers.forEach(user -> System.out.println(user));

 }

 @Test
 public void testGetUserById(){
        User userNam=repo.findById(1).get();
        assertThat(userNam).isNotNull();
 }


    @Test
    public void testGetUserByEmail() {
        String email = "kenkwoelizabeth78@gmail.com";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testCountById() {
       Integer id=1;
Long countById= repo.countById(id);
        assertThat(countById).isNotNull();
    }
    }