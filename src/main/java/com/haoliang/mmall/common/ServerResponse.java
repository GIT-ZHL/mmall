package com.haoliang.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * 响应对象类
 *
 * @param <T> 响应数据的数据类型
 */
//JSON序列化：如果属性对应的值是null，序列化时将不会处理该属性
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 判断响应是否成功
     *
     * @return true 响应成功 false 响应失败
     */
    @JsonIgnore
    //不会出现在序列化对象中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 获得响应状态
     *
     * @return 响应状态
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 获得响应信息
     *
     * @return 响应信息
     */
    public String getMsg() {
        return this.msg;
    }

    /**
     * 获得响应数据
     *
     * @return
     */
    public T getData() {
        return this.data;
    }

    /**
     * 创建响应成功对象
     *
     * @param <T> 响应数据的数据类型
     * @return 响应成功的响应对象
     */
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 创建带有响应消息的响应成功对象
     *
     * @param message 响应消息
     * @param <T>     响应数据类型
     * @return 带有响应消息的响应成功对象
     */
    public static <T> ServerResponse<T> createBySuccessMessage(String message) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    /**
     * 创建带有响应数据的响应成功对象
     *
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return 带有响应数据的响应成功对象
     */
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     * 创建带有响应消息和相应数据的响应成功对象
     *
     * @param message 响应消息
     * @param data    响应数据
     * @param <T>     响应数据类型
     * @return 带有响应消息和相应数据的响应成功对象
     */
    public static <T> ServerResponse<T> createBySuccess(String message, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 创建响应失败对象
     *
     * @param <T> 响应数据类型
     * @return 响应失败对象
     */
    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    /**
     * 创建带有响应信息响应失败对象
     *
     * @param errorMessage 响应信息
     * @param <T>响应数据类型
     * @return 响应失败对象
     */
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    /**
     * 创建带有响应信息和响应码响应失败对象
     *
     * @param errorCode    响应码
     * @param errorMessage 响应信息
     * @param <T>          响应数据类型
     * @return 响应失败对象
     */
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }

}
