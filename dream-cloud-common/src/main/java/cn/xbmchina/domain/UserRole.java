package cn.xbmchina.domain;

import java.io.Serializable;

public class UserRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Integer roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}