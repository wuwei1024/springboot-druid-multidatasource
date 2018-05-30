package com.test.service.impl;

import com.test.entity.Result;
import com.test.service.ApiService;
import com.test.util.TokenUtil;
import org.springframework.stereotype.Service;

/**
 * @author wuwei
 * @date 2018/5/29 17:29
 */
@Service
public class ApiServiceImpl implements ApiService {

    /**
     * 获取accessToken
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result getAccessToken(String username, String password) {
        //TODO 验证用户名和密码
        return Result.getSuccessResult(TokenUtil.getToken(username, password));
    }
}
