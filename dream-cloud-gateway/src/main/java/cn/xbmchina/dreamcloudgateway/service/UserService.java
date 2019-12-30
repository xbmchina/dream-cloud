package cn.xbmchina.dreamcloudgateway.service;

import cn.xbmchina.domain.User;
import cn.xbmchina.vo.UserRoleVo;
import com.github.pagehelper.PageInfo;

public interface UserService {

    public User findUserByName(String name);

    /**
     * 根据条件分页查询
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> selectByPage(User user, int pageNum, int pageSize);

    public void saveUserRoles(UserRoleVo userRole);

    public void addUser(User user);

    public void delUser(Long id);

    public User queryByName(String username);

    User validToken(String token);
     String getToken(User user);
}
