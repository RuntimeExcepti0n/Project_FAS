package com.paic.cost.annotation;

import com.paic.cost.validate.TimeAfterValidator;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 11:24
 */
@Constraint(validatedBy = TimeAfterValidator.class)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeAfter {

    Class<?>[] groups() default {};

    /**
     * 开始时间
     * @return
     */
    String beforeTimeFeild();

    /**
     * 结束时间
     * @return
     */
    String afterTimeFeild();

    Class<? extends Payload>[] payload() default {};

    String message() default "";

    @Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        TimeAfter[] value();
    }

}
