package com.frame.service;

import com.frame.dao.UserDao;
import com.frame.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User add(User user) {
        String passwordHash = passwordToHash(user.getPassword());
        user.setPassword(passwordHash);
        userDao.add(user);
        return findById(user.getId());
    }

    public User findById(int id) {
        User user = new User();
        user.setId(id);
        return userDao.findOne(user);
    }

    public User findByName(String name) {
        User param = new User();
        param.setUsername(name);
        return userDao.findOne(param);
    }

    private String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            // https://my.oschina.net/u/347386/blog/182717
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public boolean comparePassword(User user, User userInDataBase) {
        return passwordToHash(user.getPassword())      // 将用户提交的密码转换为 hash
                .equals(userInDataBase.getPassword()); // 数据库中的 password 已经是 hash，不用转换
    }
}
