package com.waibao.demo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author zty
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private static final long serialVersionUID = 1L;
}