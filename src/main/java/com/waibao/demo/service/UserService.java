package com.waibao.demo.service;


import com.waibao.demo.entity.User;
import com.waibao.demo.form.CompanyForm;
import com.waibao.demo.form.LoginForm;
import com.waibao.demo.form.UserRegisterForm;
import com.waibao.demo.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserService
 * @Description 操作用户
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

public interface UserService {

    /**
     * 增加用户
     *
     * @param registerForm
     * @return
     */
    ResultVO addUser(UserRegisterForm registerForm);

    /**
     * 增加用户
     *
     * @param registerForm
     * @return
     */
    ResultVO addAdmin(UserRegisterForm registerForm);

    /**
     * 获取当前用户
     *
     * @return
     */
    User getCurrentUser();
    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    User getUserByUsername(String userName);

    /**
     * 登录
     *
     * @param loginForm
     * @param response
     * @return
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response);


    ResultVO addCompany(CompanyForm companyForm);




}
