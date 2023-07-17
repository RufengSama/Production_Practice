package com.buct.spider.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Data
@Accessors(chain = true)
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stuNo;

    private String realName;

    private String className;

    private String atcoderId;

    private Integer schoolName;

    private String codeforcesId;

    private Integer year;


}
