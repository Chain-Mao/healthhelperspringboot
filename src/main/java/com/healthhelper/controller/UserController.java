package com.healthhelper.controller;

import com.healthhelper.common.Const;
import com.healthhelper.common.ResponseCode;
import com.healthhelper.pojo.User;
import com.healthhelper.server.IUserService;
import com.healthhelper.utils.ServerResponse;
import com.healthhelper.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController //负责把ServerResponse转成json
@RequestMapping("/portal/") //portal代表前端，在类url中加前缀
public class UserController {

/*    //登录 login.do路径?username=xxx&password=xxx
    @RequestMapping(value = {"/login.do", "/login"})
    public UserInfo login(@RequestParam(value = "username",required = true,defaultValue = "未命名用户") String username,
                          @RequestParam(value = "password") String password){

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        return userInfo;
    }
    //restful风格接口地址 路径/restful/login/username=xxx/password=xxx
    @RequestMapping(value = {"/restful/login/{username}/{password}"})
    public UserInfo loginRestful(@PathVariable(value = "username") String username,
                            @PathVariable(value = "password") String password){

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        return userInfo;
    }*/
    @Autowired
    IUserService userService;

    @RequestMapping(value = "user/login.do")
    public ServerResponse login(String username, String password, HttpSession session){   //ServerResponse为往前端返回的高可用对象
        ServerResponse serverResponse = userService.loginLogic(username,password);
        if(serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());  //session中存贮userVO数据
        }
        return serverResponse;
    }

    @RequestMapping("user/register.do")
    public ServerResponse register(User user){
        return userService.registerLogic(user);
    }

    @RequestMapping("user/update.do")
    public ServerResponse updateUser(User user,HttpSession session){
        //判断用户是否登录
        UserVO userInfo = (UserVO)session.getAttribute(Const.CURRENT_USER);
        //已经有拦截器，不再需要
/*        if(userInfo == null){
            return  ServerResponse.createServerResponseByFail(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
        }
        if(user == null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMENT_NOT_EMPTY.getCode(),ResponseCode.PARAMENT_NOT_EMPTY.getMsg());
        }*/
        user.setId(userInfo.getId());
        ServerResponse serverResponse = userService.updateUserLogic(user);
        if(serverResponse.isSuccess()){//更新session中的用户信息
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }
}


