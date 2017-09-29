package com.zero.customer.annotation;

import java.lang.annotation.*;

/**
 * @author yezhaoxing
 * @date 2017/09/29
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorize {
}
