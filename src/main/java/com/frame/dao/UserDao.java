package com.frame.dao;

import com.frame.entity.User;

public interface UserDao {
    int add(User user);

    User findOne(User user);
}
