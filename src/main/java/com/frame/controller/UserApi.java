package com.frame.controller;

import com.alibaba.fastjson.JSONObject;
import com.frame.annotation.LoginRequired;
import com.frame.entity.User;
import com.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Object add(@RequestBody User user) {
        if (userService.findByName(user.getUsername()) != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "用户名已被使用");
            return jsonObject;
        }
        return userService.add(user);
    }

    @LoginRequired
    @PostMapping("/getMessage")
    public String getMessage() {
        return "success";
    }
}
