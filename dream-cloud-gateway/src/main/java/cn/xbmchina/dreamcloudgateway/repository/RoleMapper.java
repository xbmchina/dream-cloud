package cn.xbmchina.dreamcloudgateway.repository;

import cn.xbmchina.bo.UserRoleBo;
import cn.xbmchina.domain.Role;
import cn.xbmchina.domain.RoleExample;
import cn.xbmchina.domain.RoleResources;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<UserRoleBo> queryRoleListWithUser(Long userId);

    int deleteRoleResources(Integer roleId);

    int addRoleResources(RoleResources roleResources);
}