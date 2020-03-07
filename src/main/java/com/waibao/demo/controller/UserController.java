package com.waibao.demo.controller;

import com.waibao.demo.form.CompanyForm;
import com.waibao.demo.form.LoginForm;
import com.waibao.demo.form.UserRegisterForm;
import com.waibao.demo.service.UserService;
import com.waibao.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author zty
 * @Date 2020/3/4 上午9:05
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultVO login(LoginForm loginForm, HttpServletResponse response){
        return userService.login(loginForm,response);
    }

    @ApiOperation("注册普通用户")
    @PostMapping("/register")
    public ResultVO userRegister(UserRegisterForm registerForm){
        return userService.addUser(registerForm);
    }

    @ApiOperation("注册管理员用户")
    @PostMapping("/registerAdmin")
    public ResultVO adminRegister(UserRegisterForm registerForm){
        return userService.addAdmin(registerForm);
    }

    @ApiOperation("添加公司")
    @PostMapping("/addCompany")
    public ResultVO addCompany(CompanyForm companyForm){
        return userService.addCompany(companyForm);
    }
}
