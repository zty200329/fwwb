package com.waibao.demo.service.impl;

import com.waibao.demo.service.FileService;
import com.waibao.demo.util.FileUtil;
import com.waibao.demo.util.ResultVOUtil;
import com.waibao.demo.vo.PictureBackVO;
import com.waibao.demo.vo.ResultVO;
import com.waibao.demo.vo.VideoBackVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.handler.LogicalHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zty
 * @Date: 2020/2/14 15:17
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileUtil fileUtil;

    @Value("${filepath.video}")
    private String videoPath;
    @Value("${filepath.picture}")
    private String picturePath;

    private String url = "http://zty200329.nat300.top/";
    @Override
    public ResultVO showPicture() {
        List<PictureBackVO> pictureVOList = new ArrayList();
        ArrayList<File> list = FileUtil.getListFiles(picturePath);

        for (File file : list) {
            PictureBackVO pictureBackVO = new PictureBackVO();
            pictureBackVO.setFileName(file.getName());
            pictureBackVO.setPictureUrl(url+"picture/"+file.getName());
            pictureBackVO.setLastDate(file.lastModified());
            pictureVOList.add(pictureBackVO);
            log.info(file.getName());
            log.info(url+"picture/"+file.getName());
            log.info("-----------------------------");
        }
        return ResultVOUtil.success(pictureVOList);
    }

    @Override
    public ResultVO showVideo() {
        ArrayList<File> list = FileUtil.getListFiles(videoPath);
        List<VideoBackVO> videoBackVOS = new ArrayList<>();

        for (File file : list) {
            VideoBackVO videoBackVO = new VideoBackVO();
            videoBackVO.setFileName(file.getName());
            videoBackVO.setPictureUrl(url+"video/"+file.getName());
            videoBackVO.setLastDate(file.lastModified());
            videoBackVOS.add(videoBackVO);
            log.info(file.getName());
            log.info(url+"video/"+file.getName());
            log.info("-----------------------------");
        }
        videoBackVOS.remove(videoBackVOS.size()-1);
        return ResultVOUtil.success(videoBackVOS);
    }
}
