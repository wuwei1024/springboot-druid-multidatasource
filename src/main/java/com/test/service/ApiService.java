package com.test.service;

import com.test.entity.Result;

/**
 * @author wuwei
 * @date 2018/5/29 17:28
 */
public interface ApiService {
    Result getAccessToken(String username, String password);
}
