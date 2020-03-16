package com.paic.cost.annotation;

import java.lang.annotation.*;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/27 22:52
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessFunc {
    Class<?>[] beforeFunc() default {};

    Class<?>[] afterFunc() default {};

    Class<?>[] overFunc() default {};

}
