package cn.xbmchina.bo;

import cn.xbmchina.domain.Role;

public class UserRoleBo extends Role {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer selected;

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "UserRoleBo [selected=" + selected + "]";
    }

}
