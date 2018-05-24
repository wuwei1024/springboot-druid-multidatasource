package com.test.dao.write;

import com.test.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author wuwei
 * @date 2018/5/24 11:47
 */
@Repository
public interface WriteMapper {
    int addUser(User user);

    int updateUser(User user);

    int delUser(String userId);

    int delUsers(String[] userIds);
}
