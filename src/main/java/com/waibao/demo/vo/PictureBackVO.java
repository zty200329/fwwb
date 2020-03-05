package com.waibao.demo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: zty
 * @Date: 2020/2/14 15:23
 */
@Data
public class PictureBackVO {
    /**
     * 图片名字
     */
    private String fileName;

    /**
     * url
     */
    private  String pictureUrl;

    /**
     * 最后修改时间
     */
    private Long lastDate;
}
