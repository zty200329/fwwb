package com.waibao.demo.service;

import com.waibao.demo.form.IpCameraForm;
import com.waibao.demo.vo.ResultVO;

/**
 * @Author zty
 * @Date 2020/3/4 下午10:40
 * @Description:
 */
public interface IpCameraService {
    /**
     * 添加摄像头
     * @param ipCameraForm
     * @return
     */
    ResultVO addCamera(IpCameraForm ipCameraForm);


    ResultVO deleteCamera(Integer id);

    ResultVO showList();

}
