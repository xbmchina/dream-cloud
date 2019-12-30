package cn.xbmchina.dreamcloudgateway.service;

import cn.xbmchina.bo.UserRoleBo;
import cn.xbmchina.domain.Role;
import cn.xbmchina.domain.RoleResources;
import cn.xbmchina.vo.RoleResourcesVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {


    public PageInfo<Role> selectByPage(Role role, int pageNum, int pageSize);

    /**
     * 查询所有角色，并且包含当前用户是否选中
     * @param uid
     * @return
     */
    public List<UserRoleBo> queryRoleListWithUser(Long uid);

    public void saveRoleResources(RoleResourcesVo roleResourcesVo);

    public void addRole(Role role);

    public void delRole(Integer id);

}
