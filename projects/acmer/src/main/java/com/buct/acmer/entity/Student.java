package com.buct.acmer.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Getter
@Setter
@ApiModel(value = "Student对象", description = "")
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
