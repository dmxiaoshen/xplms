package com.hsg.plms.base.entity;

import java.io.Serializable;

public class ShiroUser implements Serializable {

    /** */
    private static final long serialVersionUID = -8167341999489366817L;

    private Long userId;
    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
