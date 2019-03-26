package com.you.online_exam.mapper;

import com.you.online_exam.entity.UserPaperAnswer;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface UserPaperAnswerMapper extends BaseMapper<UserPaperAnswer> {

}
