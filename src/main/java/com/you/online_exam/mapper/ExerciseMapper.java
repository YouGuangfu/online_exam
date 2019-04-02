package com.you.online_exam.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.you.online_exam.entity.Exercise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 尤广富
 * @since 2019-03-26
 */
@Mapper
@Component
public interface ExerciseMapper extends BaseMapper<Exercise> {

}
