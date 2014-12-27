
package com.hsg.plms.user.service;

import com.hsg.plms.base.entity.Page;
import com.hsg.plms.base.entity.Pagination;
import com.hsg.plms.user.entity.User;

public interface UserService {
    
    void saveUser(User u);

    User findUserByName(String username);
    
    User findById(Long userId);

    void deleteUserById(Long userId);

    Pagination<User> queryUserData(Page page);
}
