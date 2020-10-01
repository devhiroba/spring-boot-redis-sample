package com.bo.sample.redis.dao;

import com.bo.sample.redis.model.User;

public interface IUserDao {

    String addUser(User user);

    User getUser(long id);

    long delUser(long id);

}
