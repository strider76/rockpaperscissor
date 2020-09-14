package com.cicklum.paperrockscissor.aplication.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cicklum.paperrockscissor.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;

@SpringBootTest
class AplicationUserServiceImplTest {

    @Autowired
    private AplicationUserService aplicationUserService;


    @Test
    void testCreateUser() throws UserDuplicatedException {
        Assertions.assertEquals(
                "testCreate"
                ,this.aplicationUserService.createUser("testCreate","testCreate").getUserName());
    }


    @Test
    void testFindUserOk() throws UserDuplicatedException {
        this.aplicationUserService.createUser("test","test");
        Assertions.assertTrue(this.aplicationUserService.findByUserName("test").isPresent());
    }

    @Test
    void testCreateUserAlreadyExist() throws UserDuplicatedException {
        this.aplicationUserService.createUser("test2","test2");
        Assertions.assertThrows(UserDuplicatedException.class, () -> this.aplicationUserService.createUser("test","test"));
    }



}