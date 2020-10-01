package com.bo.sample.redis.controller;

import com.bo.sample.redis.dao.IUserDao;
import com.bo.sample.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserDao userDao;

    @Autowired
    public UserController(IUserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id)
    {
        return userDao.getUser(id);
    }

    @PostMapping
    public String addUser(@RequestBody User user){
        return userDao.addUser(user);
    }

    @DeleteMapping("/{id}")
    public Long delUser(@PathVariable long id){
        return userDao.delUser(id);
    }
}
