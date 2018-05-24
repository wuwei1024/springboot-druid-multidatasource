package com.test.service;

import com.test.entity.Result;
import com.test.entity.User;

/**
 * @author wuwei
 * @date 2018/5/24 9:40
 */
public interface UserService {
    Result addUser(User user);

    Result findAllUser();

    Result findUserById(String userId);
}
