package com.waibao.demo.dao;

import com.waibao.demo.entity.UserCompany;
import org.springframework.stereotype.Component;

@Component
public interface UserCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCompany record);

    int insertSelective(UserCompany record);

    UserCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCompany record);

    int updateByPrimaryKey(UserCompany record);
}