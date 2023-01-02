package org.bs.common.core.validator;

import cn.hutool.core.bean.BeanUtil;
import org.bs.common.core.validator.anno.CompareStrings;
import org.bs.common.core.enums.StringComparisonMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author :wkh
 */
public class CompareStringsValidator implements ConstraintValidator<CompareStrings, Object> {

    private String[] propertyNames;
    private StringComparisonMode comparisonMode;
    private boolean allowNull;

    @Override
    public void initialize(CompareStrings constraintAnnotation) {
        this.propertyNames = constraintAnnotation.propertyNames();
        this.comparisonMode = constraintAnnotation.matchMode();
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        boolean isValid = true;
        List<String> propertyValues = new ArrayList<>(propertyNames.length);
        for (String propertyName : propertyNames) {
            String propertyValue = Optional.ofNullable(BeanUtil.getFieldValue(target, propertyName)).orElse("").toString();
            if (propertyValue == null) {
                if (!allowNull) {
                    isValid = false;
                    break;
                }
            } else {
                propertyValues.add(propertyValue);
            }
        }

        if (isValid) {
            isValid = ConstraintValidatorHelper.isValid(propertyValues, comparisonMode);
        }

        if (!isValid) {
            String message = context.getDefaultConstraintMessageTemplate();
            context.disableDefaultConstraintViolation();
            for (String propertyName : propertyNames) {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(propertyName)
                        .addConstraintViolation();
            }
        }

        return isValid;
    }
}
