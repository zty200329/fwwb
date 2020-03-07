package com.waibao.demo.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zty
 * @Date 2020/3/3 下午11:09
 * @Description:
 */
@Data
public class UserRegisterForm {

    private String username;

    private String password;

    private String company;

    private String companyPassword;

}
