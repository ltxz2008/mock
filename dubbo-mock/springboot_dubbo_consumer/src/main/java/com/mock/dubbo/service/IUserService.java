package com.mock.dubbo.service;

import com.mock.dubbo.domain.User;

/**
 * Mock service API
 * 
 * @author duanzongxiang1
 *
 */
public interface IUserService {
    void saveUser(User user);

    User getUserById(int id);
    
    User getUserInfo(User user);
}
