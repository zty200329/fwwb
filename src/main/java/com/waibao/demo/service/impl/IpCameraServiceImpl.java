package com.waibao.demo.service.impl;

import com.waibao.demo.dao.IpCameraMapper;
import com.waibao.demo.entity.IpCamera;
import com.waibao.demo.enums.ResultEnum;
import com.waibao.demo.form.IpCameraForm;
import com.waibao.demo.service.IpCameraService;
import com.waibao.demo.util.RedisUtil;
import com.waibao.demo.util.ResultVOUtil;
import com.waibao.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zty
 * @Date 2020/3/4 下午10:48
 * @Description:
 */
@Slf4j
@Service
public class IpCameraServiceImpl implements IpCameraService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IpCameraMapper ipCameraMapper;

    private String key = "ipCamera";

    @Override
    public ResultVO addCamera(IpCameraForm ipCameraForm) {
        IpCamera ipCamera = new IpCamera();
        BeanUtils.copyProperties(ipCameraForm,ipCamera);
        ipCameraMapper.insert(ipCamera);
        redisUtil.del(key);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO deleteCamera(Integer id) {
        if(ipCameraMapper.deleteByPrimaryKey(id)!=0){
            redisUtil.del(key);
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.DELETE_ERROR);
    }

    @Override
    public ResultVO showList() {
        if(redisUtil.get(key)==null){
            List<IpCamera> ipCameras = ipCameraMapper.selectAll();
            redisUtil.set(key,ipCameras);
            log.info("mysql查找");
            return ResultVOUtil.success(ipCameras);
        }else {
            List<IpCamera> ipCameras = (List<IpCamera>) redisUtil.get(key);
            log.info("redis查找");
            return ResultVOUtil.success(ipCameras);
        }
    }
}
