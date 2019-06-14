package com.zero.order.annotation;

import java.lang.annotation.*;

/**
 * @description 安全校验注解
 * @author yezhaoxing
 * @date 2017/09/29
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecurityTag {
}
