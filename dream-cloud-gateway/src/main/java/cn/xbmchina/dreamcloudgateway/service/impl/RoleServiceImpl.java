package cn.xbmchina.dreamcloudgateway.service.impl;

import cn.xbmchina.bo.UserRoleBo;
import cn.xbmchina.domain.Role;
import cn.xbmchina.domain.RoleExample;
import cn.xbmchina.domain.RoleResources;
import cn.xbmchina.dreamcloudgateway.repository.RoleMapper;
import cn.xbmchina.dreamcloudgateway.repository.UserMapper;
import cn.xbmchina.dreamcloudgateway.service.RoleService;
import cn.xbmchina.vo.RoleResourcesVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Role> selectByPage(Role role, int pageNum, int pageSize) {
        //分页查询
        PageHelper.startPage(pageNum, pageSize);
        RoleExample roleExample = new RoleExample();
        if (role!=null) {
            RoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andIdEqualTo(role.getId());
            criteria.andNameEqualTo(role.getName());
        }
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        return new PageInfo<>(roleList);
    }

    @Override
    public List<UserRoleBo> queryRoleListWithUser(Long uid) {
        return roleMapper.queryRoleListWithUser(uid);
    }

    @Override
//    @CacheEvict(cacheNames="myCache",allEntries=true)
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void saveRoleResources(RoleResourcesVo roleResourcesVo) {

        Integer roleId = roleResourcesVo.getRoleId();
        String resourcesIds = roleResourcesVo.getResourcesId();
        //先删除
        roleMapper.deleteRoleResources(roleId);
        //添加
        if(StringUtils.isNotBlank(resourcesIds)){
            String[] resourcesIdArray = resourcesIds.split(",");
            for (String resourcesId : resourcesIdArray) {
                if (StringUtils.isNotBlank(resourcesId)){
                    RoleResources roleResources = new RoleResources();
                    roleResources.setRoleId(roleId);
                    roleResources.setResourcesId(Integer.valueOf(resourcesId));
                    roleMapper.addRoleResources(roleResources);
                }
            }
        }
    }

    @Override
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void delRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

}
