package com.healthhelper.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 *封装前端返回的同一实体类
 **/
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)  //include只包含非空字段
public class ServerResponse<T> {

    private int status; //状态0：接口调用成功
    private T data; //当status=，将返回的数据封装data中
    private String msg; //提示信息

    private ServerResponse(){}
    private ServerResponse(int status){
        this.status=status;
    }

    private ServerResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int status,T data,String msg){
        this.status=status;
        this.data=data;
        this.msg=msg;
    }

    @JsonIgnore //忽略isSuccess的json属性
    public boolean isSuccess(){
        return this.status==0;
    }
    /**
     *调用接口成功时回调
     * */
    public static ServerResponse createServerResponseBySuccess(){
        return new ServerResponse(0);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data){
        return new ServerResponse(0,data);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data,String msg){
        return new ServerResponse(0,data,msg);
    }

    /**
     * 接口调用失败时回调
     * */
    public static ServerResponse createServerResponseByFail(int status){
        return new ServerResponse(status);
    }

    public static <T> ServerResponse createServerResponseByFail(int status,String msg){
        return new ServerResponse(status,null,msg);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
