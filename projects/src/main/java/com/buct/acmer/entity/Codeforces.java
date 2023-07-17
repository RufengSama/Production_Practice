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
 * @since 2023-07-17
 */
@Getter
@Setter
@ApiModel(value = "Codeforces对象", description = "")
public class Codeforces implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "cf_id", type = IdType.INPUT)
    private String cfId;

    @ApiModelProperty("比赛名称")
    private String cfContest;

    @ApiModelProperty("比赛名称id")
    private String cfContestId;

    @ApiModelProperty("比赛的排名")
    private String cfRank;

    @ApiModelProperty("旧积分")
    private String cfOldRating;

    @ApiModelProperty("最新积分")
    private String cfNewRating;

    @ApiModelProperty("参与比赛的总数")
    private String cfSumContest;

    @ApiModelProperty("日期")
    private String cfDate;


}
