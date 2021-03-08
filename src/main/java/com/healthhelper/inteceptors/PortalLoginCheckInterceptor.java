package com.healthhelper.inteceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthhelper.common.Const;
import com.healthhelper.common.ResponseCode;
import com.healthhelper.utils.ServerResponse;
import com.healthhelper.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//拦截器
@Component  //Bean交给IOC容器管理
public class PortalLoginCheckInterceptor implements HandlerInterceptor {

/*    请求到达controller之前
    @return true：可以通过拦截器，不拦截请求 false：拦截请求*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("=======preHandle========");

        HttpSession session = request.getSession();
        UserVO userVO = (UserVO) session.getAttribute(Const.CURRENT_USER);
        if(userVO!=null){
            //已经登录
            return true;
        }

        //用户未登录，重写Response
        try {
            response.reset();
            response.addHeader("Content-Type","application/json;charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            ServerResponse serverResponse = ServerResponse.createServerResponseByFail(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getMsg());
            ObjectMapper objectMapper = new ObjectMapper();
            String info = objectMapper.writeValueAsString(serverResponse);
            printWriter.write(info);
        }   catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    //在请求处理完成后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=======postHandle========");
    }

    //客户端接收到响应后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=======afterHandle========");
    }
}
