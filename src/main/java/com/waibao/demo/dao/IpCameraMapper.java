package com.waibao.demo.dao;

import com.waibao.demo.entity.IpCamera;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IpCameraMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IpCamera record);

    int insertSelective(IpCamera record);

    IpCamera selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IpCamera record);

    int updateByPrimaryKey(IpCamera record);

    List<IpCamera> selectAll();
}