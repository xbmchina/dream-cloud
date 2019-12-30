package cn.xbmchina.domain;

import java.io.Serializable;

public class RoleResources implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer roleId;//角色id
    private Integer resourcesId;//资源id

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }
}
