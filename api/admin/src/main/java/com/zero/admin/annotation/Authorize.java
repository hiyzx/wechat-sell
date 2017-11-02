package com.zero.admin.annotation;

import java.lang.annotation.*;

/**
 * @author yezhaoxing
 * @date 2017/11/02
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorize {
}
