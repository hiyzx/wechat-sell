package com.zero.user.annotation;

import java.lang.annotation.*;

/**
 * @description 权限校验注解
 * @author yezhaoxing
 * @date 2017/09/29
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorize {
}
