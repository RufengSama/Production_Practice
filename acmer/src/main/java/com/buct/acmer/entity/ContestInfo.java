package com.buct.acmer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-05-29
 */
@Getter
@Setter
@TableName("contest_info")
@ApiModel(value = "ContestInfo对象", description = "")
public class ContestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学号")
    @TableId(value = "stu_no", type = IdType.INPUT)
    private String stuNo;

    @ApiModelProperty("姓名")
    private String stuName;

    @ApiModelProperty("班级")
    private String stuClass;

    @ApiModelProperty("用户id")
    private Integer stuId;

    @ApiModelProperty("用户名")
    private String stuUname;

    @ApiModelProperty("时间")
    private String conDate;

    @ApiModelProperty("难度值")
    private String conDiv;

    @ApiModelProperty("类型")
    private String conType;

    @ApiModelProperty("排名")
    private Integer conRank;

    @ApiModelProperty("新积分")
    private Integer conNewrating;

    @ApiModelProperty("积分变化")
    private String conDiff;

    @ApiModelProperty("解题数")
    private String conSolve;

    @ApiModelProperty("补题数")
    private String conAftersolve;


}
