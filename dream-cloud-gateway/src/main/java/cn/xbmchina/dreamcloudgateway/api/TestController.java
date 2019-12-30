package cn.xbmchina.dreamcloudgateway.api;

import cn.xbmchina.domain.User;
import cn.xbmchina.dreamcloudgateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test1")
    public String test1() {
        return "heheh";
    }


    @RequestMapping("/test1/fext")
    public String test1f() {
        return "heheh";
    }

    @RequestMapping("/test2")
    public String test2() {
        return "heheh";
    }

    @RequestMapping("/test2/gggg")
    public String test2g() {
        return "heheh";
    }

    @RequestMapping("/user/getToken")
    public String getToken(User user) {
        return userService.getToken(user);
    }
}
