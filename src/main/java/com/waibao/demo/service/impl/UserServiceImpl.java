package com.waibao.demo.service.impl;


import com.waibao.demo.dao.CompanyMapper;
import com.waibao.demo.dao.UserCompanyMapper;
import com.waibao.demo.dao.UserMapper;
import com.waibao.demo.entity.Company;
import com.waibao.demo.entity.User;
import com.waibao.demo.entity.UserCompany;
import com.waibao.demo.enums.ResultEnum;
import com.waibao.demo.form.CompanyForm;
import com.waibao.demo.form.LoginForm;
import com.waibao.demo.form.UserRegisterForm;
import com.waibao.demo.security.JwtProperties;
import com.waibao.demo.security.JwtUserDetailServiceImpl;
import com.waibao.demo.service.UserService;
import com.waibao.demo.util.JwtTokenUtil;
import com.waibao.demo.util.RedisUtil;
import com.waibao.demo.util.ResultVOUtil;
import com.waibao.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zty
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserCompanyMapper userCompanyMapper;



    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String key = "anonymousUser";
        if (!userName.equals(key)) {
            return getUserByUsername(userName);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String userName) {
        return userMapper.selectByUserName(userName);
    }


    /**
     * @param loginForm
     * @param response
     * @return
     */
    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        User user = userMapper.selectByUserName(loginForm.getUserName());
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getUserName());
        if (!(new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword()))) {
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(), realToken);

        Map map = new HashMap();
        map.put("username", user.getUsername());
        map.put("role", user.getRole());
        map.put("token", realToken);

        return ResultVOUtil.success(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO addCompany(CompanyForm companyForm) {
        Company company1 = companyMapper.selectByCompanyName(companyForm.getCompany());
        if(company1 != null){
            return ResultVOUtil.error(ResultEnum.COMPANY_NOT_EXIST);
        }
        Company company = new Company();
        BeanUtils.copyProperties(companyForm,company);
        companyMapper.insert(company);
        return ResultVOUtil.success();
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO addUser(UserRegisterForm registerForm) {
        User test = userMapper.selectByUserName(registerForm.getUsername());
        if (test != null) {
            return ResultVOUtil.error(ResultEnum.USER_HAS_EXIST);
        }
        Company company1 = companyMapper.selectByCompanyName(registerForm.getCompany());
        if(company1 == null){
            return ResultVOUtil.error(ResultEnum.COMPANY_NOT_EXIST);
        }
        if(!registerForm.getCompanyPassword().equals(company1.getCompanyPassword())){
            return ResultVOUtil.error(ResultEnum.COMPANY_PASSWORD_ERROR);
        }

        User user = new User();
        BeanUtils.copyProperties(registerForm, user);

        user.setRole(0);
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        log.info("用户信息" + user);
        int result = userMapper.insert(user);
        UserCompany userCompany = new UserCompany();
        userCompany.setCompanyId(company1.getId());
        assert test != null;
        userCompany.setUserId(user.getId());
        userCompanyMapper.insert(userCompany);
        if (result != 1) {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO addAdmin(UserRegisterForm registerForm) {
        User test = userMapper.selectByUserName(registerForm.getUsername());
        if (test != null) {
            return ResultVOUtil.error(ResultEnum.USER_HAS_EXIST);
        }
        Company company1 = companyMapper.selectByCompanyName(registerForm.getCompany());
        if(company1 == null){
            return ResultVOUtil.error(ResultEnum.COMPANY_NOT_EXIST);
        }
        if(!registerForm.getCompanyPassword().equals(company1.getCompanyPassword())){
            return ResultVOUtil.error(ResultEnum.COMPANY_PASSWORD_ERROR);
        }

        User user = new User();
        BeanUtils.copyProperties(registerForm, user);

        user.setRole(1);
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        log.info("用户信息" + user);
        int result = userMapper.insert(user);
        UserCompany userCompany = new UserCompany();
        userCompany.setCompanyId(company1.getId());
        assert test != null;
        userCompany.setUserId(user.getId());
        userCompanyMapper.insert(userCompany);
        if (result != 1) {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }


}
