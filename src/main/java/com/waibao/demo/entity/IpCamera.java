package com.waibao.demo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * ip_camera
 * @author 
 */
@Data
public class IpCamera implements Serializable {
    private Integer id;

    private String ip;

    private String address;

    private static final long serialVersionUID = 1L;
}