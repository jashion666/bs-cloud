package org.bs.api.validator.anno;


import org.bs.api.validator.CompareStringsValidator;
import org.bs.common.core.enums.StringComparisonMode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompareStringsValidator.class)
public @interface CompareStrings {
    String[] propertyNames();

    StringComparisonMode matchMode() default StringComparisonMode.EQUAL;

    boolean allowNull() default false;

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
