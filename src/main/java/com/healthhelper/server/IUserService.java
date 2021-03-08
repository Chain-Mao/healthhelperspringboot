package com.healthhelper.server;

import com.healthhelper.pojo.User;
import com.healthhelper.utils.ServerResponse;

public interface IUserService {
    /**
     * 登录
     */
    public ServerResponse loginLogic(String username, String password);

    /**
     * 注册
     */
    public ServerResponse registerLogic(User user);

    /**
     * 修改用户信息
     */
    public ServerResponse updateUserLogic(User user);
}

