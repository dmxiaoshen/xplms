package com.hsg.plms.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.hsg.plms.base.entity.BaseEntity;
import com.hsg.plms.base.serializer.SuperEnumSerializer;
import com.hsg.plms.common.enums.UserStatusEnum;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /** */
    private static final long serialVersionUID = -1222257487943445352L;

    private String username;
    private String password;
    private UserStatusEnum status;

    @JsonSerialize(using=SuperEnumSerializer.class)
    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

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
