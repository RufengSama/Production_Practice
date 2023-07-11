package com.buct.spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "stu_no", type = IdType.INPUT)
    private String stuNo;

    private String stuName;

    private String stuClass;

    private String stuAcId;

    private String stuCfId;


}
