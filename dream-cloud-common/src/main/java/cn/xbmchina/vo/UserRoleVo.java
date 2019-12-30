package cn.xbmchina.vo;

import java.io.Serializable;

public class UserRoleVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}