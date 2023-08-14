package com.app.travelx.Database;

import com.app.travelx.database.users.User;
import com.app.travelx.database.users.UserRepository;
import com.app.travelx.database.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repo;

    @MockBean
    private ModelMapper mapper;

    private List<User> userList;

    private User testUser;

    private User testUserWithID;

    @BeforeEach
    void init() {
        this.userList = new ArrayList<>();
        this.userList.add(testUser);
        this.testUser = new User("1", "Grey", "Latveria");
    }

    @Test
    void createUserTest() {
        System.out.println("hello");
    }
}
