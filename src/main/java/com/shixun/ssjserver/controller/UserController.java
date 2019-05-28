package com.shixun.ssjserver.controller;

import com.shixun.ssjserver.dao.UserDao;
import com.shixun.ssjserver.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam(value = "id") Integer id, @RequestParam("password") String password)
    {
        Optional<UserEntity> optionalUserEntity = userDao.findById(id);
        String message;
        String stateCode;
//        if(optionalUserEntity.isPresent())

        return "ok";
    }
}
