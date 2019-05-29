package com.shixun.ssjserver.controller;

import com.shixun.ssjserver.dao.UserDao;
import com.shixun.ssjserver.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestParam(value = "id") Integer id, @RequestParam(value = "password") String password)
    {
        Optional<UserEntity> optionalUserEntity = userDao.findById(id);
        String message;
        String stateCode;
        Map<String, Object> map = new HashMap<>();
        if(!optionalUserEntity.isPresent())
        {
            stateCode = "fail";
            message  = "没有此用户";
        }
        else
        {
            UserEntity user = optionalUserEntity.get();
            if(user.getPassword().equals(password))
            {
                stateCode = "success";
                message = "登陆成功";
                map.put("user", user);
            }
            else
            {
                stateCode = "fail";
                message = "密码错误";
            }
        }
        map.put("state", stateCode);
        map.put("message", message);
        return map;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map register(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, @RequestParam(value = "email") String email)
    {
        Map<String, Object> map = new HashMap<>();
        UserEntity user = new UserEntity();
        user.setName(name);
        user.setPassword(password);
        if(email != null)
            user.setEmail(email);
        UserEntity registeredUser = userDao.save(user);
        map.put("state", "success");
        map.put("message",  "注册成功");
        map.put("user", registeredUser);
        return map;
    }
}
