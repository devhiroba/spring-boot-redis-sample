package com.bo.sample.redis.dao;

import com.bo.sample.redis.model.User;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

@Repository
public class UserDao implements IUserDao {

    private static final String KEY = "user";
    private static final String ID = "id";
    private static final String DELIMITER = ":";

    private final Gson gson = new Gson();
//    private final RedisCommands<String, String> redisSyncCommands;
    private final RedisClusterCommands<String, String> redisSyncCommands;

    @Autowired
    public UserDao(RedisClusterClient redisClusterClient) {
        this.redisSyncCommands = redisClusterClient.connect().sync();
    }

    private String key(long userId) {
        return KEY + DELIMITER + userId;
    }

    private String idKey() {
        return KEY + DELIMITER + ID;
    }

    @Override
    public String addUser(User user) {
        long id = redisSyncCommands.incr(idKey());
        user.setId(id);
        return redisSyncCommands.set(key(id), gson.toJson(user));
    }

    @Override
    public User getUser(long id) {
        String json = redisSyncCommands.get(key(id));
        return gson.fromJson(json, User.class);
    }

    @Override
    public long delUser(long id) {
        return redisSyncCommands.del(key(id));
    }
}
