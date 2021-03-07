package com.healthhelper.server.impl;
//实现类用来实现具体方法
import com.healthhelper.common.Const;
import com.healthhelper.common.ResponseCode;
import com.healthhelper.dao.UserMapper;
import com.healthhelper.pojo.User;
import com.healthhelper.server.IUserService;
import com.healthhelper.utils.DateUtil;
import com.healthhelper.utils.MD5Utils;
import com.healthhelper.utils.ServerResponse;
import com.healthhelper.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    /**
     * 登录
     */
    public ServerResponse loginLogic(String username, String password) {
        //step1:用户名和密码的非空判断
        if(StringUtils.isBlank(username)){  //null length=0 含有空格 tab制表符
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }

        //step2:查看用户名是否存在
        Integer count = userMapper.findByUsername(username);
        if(count==0){
            //用户名不存在
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EXISTS.getCode(),ResponseCode.USERNAME_NOT_EXISTS.getMsg());
        }

        //step3:根据用户名和密码查询
        User user = userMapper.findByUsernameAndPassword(username,MD5Utils.getMD5Code(password));
        if(user==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_ERROR.getCode(),ResponseCode.PASSWORD_ERROR.getMsg());
        }

        //step4:返回结果
        return ServerResponse.createServerResponseBySuccess(convert(user));
    }

    private UserVO convert(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());
        userVO.setCreatTime(DateUtil.date2String(user.getCreatTime()));
        userVO.setUpdateTime(DateUtil.date2String(user.getUpdateTime()));
        return userVO;

    }
    /**
     * 注册
     */
    @Override
    public ServerResponse registerLogic(User user) {
        //step1:参数非空校验
        if(user==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PARAMENT_NOT_EMPTY.getCode(),ResponseCode.PARAMENT_NOT_EMPTY.getMsg());
        }
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String question = user.getQuestion();
        String answer = user.getAnswer();
        String phone = user.getPhone();

        if(StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(email)){
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_NOT_EMPTY.getCode(),ResponseCode.EMAIL_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(question)){
            return ServerResponse.createServerResponseByFail(ResponseCode.QUESTION_NOT_EMPTY.getCode(),ResponseCode.QUESTION_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(answer)){
            return ServerResponse.createServerResponseByFail(ResponseCode.ANSWER_NOT_EMPTY.getCode(),ResponseCode.ANSWER_NOT_EMPTY.getMsg());
        }
        if(StringUtils.isBlank(phone)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PHONE_NOT_EMPTY.getCode(),ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }

        //step2:判断用户名是否存在
        Integer count = userMapper.findByUsername(username);
        if(count>0) {//用户名已经存在
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_EXISTS.getCode(),ResponseCode.USERNAME_EXISTS.getMsg());
        }

        //step3:判断邮箱是否存在
        Integer email_count = userMapper.findByEmail(email);
        if (email_count>0){//邮箱已经存在
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_EXISTS.getCode(),ResponseCode.EMAIL_EXISTS.getMsg());
        }

        //step4:注册
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));  //获取用户密码，用MD5加密，将密文重新复制
        user.setRole(Const.NORMAL_USER);    //设置用户的角色为普通用户
        Integer result = userMapper.insert(user);   //数据库中受影响的行数
        if(result == 0){
            //注册失败
            return ServerResponse.createServerResponseByFail(ResponseCode.REGISTER_FAIL.getCode(),ResponseCode.REGISTER_FAIL.getMsg());
        }
        return ServerResponse.createServerResponseBySuccess();
    }
}
