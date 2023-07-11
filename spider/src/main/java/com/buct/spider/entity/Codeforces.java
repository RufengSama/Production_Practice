package com.buct.spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Getter
@Setter
@ToString
public class Codeforces implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "cf_id", type = IdType.INPUT)
    private String cfId;

    /**
     * 比赛名称
     */
    private String cfContest;

    /**
     * 比赛名称id
     */
    private String cfContestId;

    /**
     * 比赛的排名
     */
    private String cfRank;

    /**
     * 旧积分
     */
    private String cfOldRating;

    /**
     * 最新积分
     */
    private String cfNewRating;

    /**
     * 参与比赛的总数
     */
    private String cfSumContest;

    /**
     * 日期
     */
    private String cfDate;


}
