package com.healthhelper.server;

import com.healthhelper.pojo.User;
import com.healthhelper.utils.ServerResponse;

public interface IUserService {
    /**
     * 登录
     */
    public ServerResponse loginLogic(String username, String password);

    /**
     * 登录
     */
    public ServerResponse registerLogic(User user);
}
