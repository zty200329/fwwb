package com.waibao.demo.vo;

import lombok.Data;

/**
 * @Author: zty
 * @Date: 2020/2/14 15:09
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
