package com.haoliang.mmall.service;

        import com.haoliang.mmall.common.ServerResponse;
        import com.haoliang.mmall.pojo.User;

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkQuestionAnswer(String username, String question, String answer);
}
