package com.test.controller;

import com.test.entity.Result;
import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuwei
 * @date 2018/5/24 9:40
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public Result addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping("/findAllUser")
    public Result findAllUser() {
        return userService.findAllUser();
    }

    @RequestMapping("/findUserById")
    public Result findUserById(@RequestParam("userId") String userId) {
        return userService.findUserById(userId);
    }

    @RequestMapping("/updateUser")
    public Result updateUser(User user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/delUser")
    public Result delUser(@RequestParam("userId") String userId) {
        return userService.delUser(userId);
    }

    @RequestMapping("/delUsers")
    public Result delUsers(@RequestParam("userIds") String userIds) {
        return userService.delUsers(userIds);
    }
}
