package com.test.service.impl;

import com.test.dao.read.ReadMapper;
import com.test.dao.write.WriteMapper;
import com.test.entity.Result;
import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wuwei
 * @date 2018/5/24 9:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ReadMapper readDao;
    @Autowired
    private WriteMapper writeDao;
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public Result addUser(User user) {
        try {
            writeDao.addUser(user);
            return Result.getSuccessResult("添加成功！");
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return Result.getFailedResult("添加失败！");
    }

    @Override
    public Result findAllUser() {
        try {
            List<User> users = readDao.findAllUser();
            return Result.getSuccessResult(users);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return Result.getFailedResult("查询失败！");
    }

    @Override
    public Result findUserById(String userId) {
        if (StringUtils.isEmpty(userId)) return Result.getFailedResult("参数不能为空！");
        try {
            User user = readDao.findUserById(userId);
            return Result.getSuccessResult(user);
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
        return Result.getFailedResult("查询失败！");
    }
}
