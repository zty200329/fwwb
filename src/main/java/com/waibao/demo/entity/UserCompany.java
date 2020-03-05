package com.waibao.demo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user_company
 * @author 
 */
@Data
public class UserCompany implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer companyId;

    private static final long serialVersionUID = 1L;
}