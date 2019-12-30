package cn.xbmchina.vo;

import java.io.Serializable;

public class RoleResourcesVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer roleId;//角色id
    private String resourcesId;//资源id

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }
}