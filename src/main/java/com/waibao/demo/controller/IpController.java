package com.waibao.demo.controller;

import com.waibao.demo.form.IpCameraForm;
import com.waibao.demo.service.IpCameraService;
import com.waibao.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zty
 * @Date 2020/3/4 下午10:18
 * @Description:
 */@Slf4j
@RestController
@RequestMapping("/ipCamera")
@Api(tags = "摄像头模块")
@CrossOrigin
public class IpController {
     @Autowired
     private IpCameraService ipCameraService;

     @PostMapping("/addCamera")
     @ApiOperation("添加摄像头")
     public ResultVO addCamera(IpCameraForm ipCameraForm){
         return ipCameraService.addCamera(ipCameraForm);
     }


     @GetMapping("/deleteCamera")
     @ApiOperation("删除摄像头")
     public ResultVO deleteCamera(Integer id){
         return ipCameraService.deleteCamera(id);
     }

     @GetMapping("/showList")
     @ApiOperation("展示摄像头列表")
     public ResultVO showList(){
         return ipCameraService.showList();
     }


}
