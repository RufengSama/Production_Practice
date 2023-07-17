package com.buct.acmer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "返回接口")
public class PublicProperty<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "数据描述")
    private String message;
    @ApiModelProperty(value = "数据")
    private T data;

}
