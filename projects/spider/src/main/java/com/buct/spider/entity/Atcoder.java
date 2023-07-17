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
 * @since 2022-06-13
 */
@Data
@Accessors(chain = true)
public class Atcoder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 比赛名称
     */
    private String name;

    private Integer participate;

    private String endTimeStamp;

    private String startTimeStamp;

    private String type;


}
