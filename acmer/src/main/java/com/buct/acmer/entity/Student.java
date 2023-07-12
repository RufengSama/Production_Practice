package com.buct.acmer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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

    @ApiModelProperty("id")
    @TableId(value = "stu_no", type = IdType.INPUT)
    private String stuNo;

    @ApiModelProperty("学生姓名")
    private String stuName;

    @ApiModelProperty("学生班级")
    private String stuClass;

    @ApiModelProperty("学生acid")
    private String stuAcId;

    @ApiModelProperty("学生cfid")
    private String stuCfId;


}
