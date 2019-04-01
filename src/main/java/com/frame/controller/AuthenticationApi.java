package com.frame.controller;

import com.alibaba.fastjson.JSONObject;
import com.frame.entity.User;
import com.frame.service.AuthenticationService;
import com.frame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationApi {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;

    @PostMapping("")
    public JSONObject login(@RequestBody User user) {
        User userInDataBase = userService.findByName(user.getUsername());
        JSONObject jsonObject = new JSONObject();
        if(null == userInDataBase){
            jsonObject.put("message","用户不存在");
        }else if(!userService.comparePassword(user,userInDataBase)){
            jsonObject.put("message","密码不正确");
        }else{
            String token = authenticationService.getToken(userInDataBase);
            jsonObject.put("token", token);
            jsonObject.put("user", userInDataBase);
        }
        return jsonObject;
    }
}
