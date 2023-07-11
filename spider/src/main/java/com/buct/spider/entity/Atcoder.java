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
 * @since 2022-06-13
 */
@Getter
@Setter
@ToString
public class Atcoder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ac_id", type = IdType.INPUT)
    private String acId;

    /**
     * 日期
     */
    private String acDate;

    /**
     * 比赛名称
     */
    private String acContest;

    /**
     * 排名
     */
    private String acRank;

    /**
     * 表现
     */
    private String acPerformance;

    /**
     * 新积分
     */
    private String acNewrating;

    /**
     * 积分变化
     */
    private String acDiff;

    /**
     * 比赛次数
     */
    private String acCount;

    /**
     * 最高积分
     */
    private String acMaxrating;


}
