package com.waibao.demo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * company
 * @author 
 */
@Data
public class Company implements Serializable {
    private Integer id;

    private String company;

    private String companyPassword;

    private static final long serialVersionUID = 1L;
}