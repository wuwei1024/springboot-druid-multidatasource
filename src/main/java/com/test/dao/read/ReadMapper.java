package com.test.dao.read;

import com.test.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wuwei
 * @date 2018/5/24 11:34
 */
@Repository
public interface ReadMapper {
    List<User> findAllUser();

    User findUserById(String userId);
}
