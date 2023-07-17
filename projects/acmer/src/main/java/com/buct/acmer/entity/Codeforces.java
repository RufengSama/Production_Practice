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
 * @since 2022-06-16
 */
@Getter
@Setter
@ApiModel(value = "Codeforces对象", description = "")
public class Codeforces implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cid;

    /**
     * 比赛名称
     */
    private String name;

    private Integer participate;

    private String endTimeStamp;

    private String startTimeStamp;

    private String type;


}
