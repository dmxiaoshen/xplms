
package com.hsg.plms.home.service;

import com.hsg.plms.home.entity.User;

public interface UserService {
    
    void saveUser(User u);

    User findUserByName(String username);
}
