package com.test.controller;

import com.test.entity.Result;
import com.test.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuwei
 * @date 2018/5/29 17:25
 */
@RestController
public class ApiController {
    @Autowired
    private ApiService apiService;

    @RequestMapping("/getAccessToken")
    public Result getAccessToken(@RequestParam("username") String username, @RequestParam("password") String password) {
        return apiService.getAccessToken(username, password);
    }
}
