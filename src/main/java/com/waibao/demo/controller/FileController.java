package com.waibao.demo.controller;

import com.waibao.demo.form.LoginForm;
import com.waibao.demo.form.UserRegisterForm;
import com.waibao.demo.service.FileService;
import com.waibao.demo.service.UserService;
import com.waibao.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zty
 * @Date: 2020/2/14 14:53
 */
@Slf4j
@RestController
@RequestMapping("/result")
@Api(tags = "回放回看操作")
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @Scheduled(fixedRate = 10000)
    @ApiOperation("获取所有违规图片")
    @GetMapping("/getPicture")
    public ResultVO showPicture(){
        return fileService.showPicture();
    }

    @ApiOperation("获取回放视频")
    @GetMapping("/getVideos")
    public ResultVO showVideo(){
        return fileService.showVideo();
    }

}
