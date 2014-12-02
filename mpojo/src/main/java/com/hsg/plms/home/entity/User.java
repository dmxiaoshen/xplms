package com.hsg.plms.home.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hsg.plms.base.entity.BaseEntity;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /** */
    private static final long serialVersionUID = -1222257487943445352L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
