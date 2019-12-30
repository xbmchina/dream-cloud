package cn.xbmchina.dreamcloudgateway.service.impl;

import cn.xbmchina.bo.UserRoleBo;
import cn.xbmchina.domain.Role;
import cn.xbmchina.domain.User;
import cn.xbmchina.domain.UserExample;
import cn.xbmchina.domain.UserRole;
import cn.xbmchina.dreamcloudgateway.config.JwtUserDetails;
import cn.xbmchina.dreamcloudgateway.config.JwtValidator;
import cn.xbmchina.dreamcloudgateway.properties.AppProperties;
import cn.xbmchina.dreamcloudgateway.repository.UserMapper;
import cn.xbmchina.dreamcloudgateway.service.RoleService;
import cn.xbmchina.dreamcloudgateway.service.UserService;
import cn.xbmchina.dreamcloudgateway.util.JwtTokenUtil;
import cn.xbmchina.vo.UserRoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AppProperties appProperties;

    public JwtTokenUtil jwtTokenUtil;


    @PostConstruct
    public void init() {
        this.jwtTokenUtil = new JwtTokenUtil(appProperties.getJwtExpirationDay(), appProperties.getJwtSecret());
    }


    /**
     * 查找用户和密码
     * @param name
     * @return
     */
    @Override
    public User findUserByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(name);
        List<User> userList = userMapper.selectByExample(example);
        if (userList!=null && userList.size() >0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 分页查找
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<User> selectByPage(User user,int pageNum, int pageSize) {
        //分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<User> userlist = userMapper.selectByExample(new UserExample());
        return new PageInfo<>(userlist);
    }

    /**
     * 保存用户与角色的关系
     * @param userRole
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void saveUserRoles(UserRoleVo userRole) {
        userMapper.delRolesByUserId(userRole.getUserId());
        String[] roleids = userRole.getRoleId().split(",");
        for (String roleId : roleids) {
            if (StringUtils.isNotBlank(roleId)){
                UserRole u = new UserRole();
                u.setUserId(userRole.getUserId());
                u.setRoleId(Integer.valueOf(roleId));
                userMapper.addUserRole(u);
            }
        }
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user){
        userMapper.insert(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void delUser(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询用户，不返回密码
     * @param username
     * @return
     */
    @Override
    public User queryByName(String username) {
        User userByName = findUserByName(username);
        if (userByName!=null) {
            userByName.setPassword("");
        }
        return userByName;
    }


    /**
     * 登录，获取token
     *
     * @return
     */
    @Override
    public String getToken(User user) {
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return null;
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users!=null && users.size() >0) {
            return this.jwtTokenUtil.generateTokenWithUsername(user.getUsername());
        }
        return null;
    }



    /**
     * 验证token正确性，并返回用户信息
     *
     * @param token
     * @return
     */
    @Override
    public User validToken(String token) {
        String username = this.jwtTokenUtil.getUsernameFromToken(token);
        if (username != null) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameEqualTo(username);
            List<User> users = userMapper.selectByExample(userExample);

            if (users!=null && users.size() >0) {
                User user = users.get(0);
                List<UserRoleBo> userRoleBos = roleService.queryRoleListWithUser(user.getId());
                if (userRoleBos!=null) {
                    List<Role> roleList = new ArrayList<>();
                    for(UserRoleBo userRoleBo : userRoleBos) {
                        if (userRoleBo.getSelected() == 1){
                            roleList.add(new Role(userRoleBo.getId(),userRoleBo.getName()));
                        }
                    }
                    user.setRoleList(roleList);
                }
                return user;
            }
        }
        return null;
    }

}
