package com.haoliang.mmall.controller.portal;

import com.haoliang.mmall.common.Const;
import com.haoliang.mmall.common.ServerResponse;
import com.haoliang.mmall.pojo.User;
import com.haoliang.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param session
     * @return 响应对象
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = this.iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return 响应对象
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 用户注册
     *
     * @param user
     * @return 响应对象
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return this.iUserService.register(user);
    }

    /**
     * 校验合法性
     *
     * @param str  校验值
     * @param type 校验类型 ['username','email']
     * @return 响应对象 不存在代表响应成功
     */
    @RequestMapping(value = "check_valid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return this.iUserService.checkValid(str, type);
    }

    /**
     * 获取用户信息
     *
     * @param session 会话
     * @return 响应对象
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createBySuccessMessage("用户未登录，无法获取当前用户信息");
    }

    /**
     * 获取找回密码问题
     * @param username 用户名
     * @return 响应对象 响应成功代表获取成功，响应失败代表获取失败
     */
    @RequestMapping(value = "get_forget_question",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> getForgetQuestion(String username){

        return this.iUserService.selectQuestion(username);
    }

    /**
     * 确认密码问题答案
     * @param username 用户名
     * @param question 忘记密码问题
     * @param answer 忘记密码问题答案
     * @return 响应对象，响应成功代表答案正确，响应失败代表答案错误
     */
    public ServerResponse<String> checkQuestionAnswer(String username,String question,String answer){

        return this.iUserService.checkQuestionAnswer(username,question,answer);
    }

}
