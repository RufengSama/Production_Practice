package com.buct.acmer.mapper;

import com.buct.acmer.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
