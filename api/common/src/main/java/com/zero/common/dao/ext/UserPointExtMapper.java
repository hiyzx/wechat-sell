package com.zero.common.dao.ext;

import org.apache.ibatis.annotations.Param;

/**
 * @author zero
 * @date 2017/08/17
 */
public interface UserPointExtMapper {

    /**
     * 增加积分
     */
    void increasePoint(@Param("userId") Integer userId, @Param("gainScore") Integer gainScore);
}
