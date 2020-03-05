package com.waibao.demo.dao;

import com.waibao.demo.entity.Company;
import org.springframework.stereotype.Component;

@Component
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    Company selectByCompanyName(String companyName);
}