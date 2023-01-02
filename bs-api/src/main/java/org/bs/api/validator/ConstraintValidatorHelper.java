package org.bs.api.validator;


import org.bs.common.core.enums.StringComparisonMode;

import java.util.*;

/**
 * @author :wkh
 */
public class ConstraintValidatorHelper {

    public static boolean isValid(Collection<String> propertyValues, StringComparisonMode comparisonMode) {
        boolean ignoreCase = false;
        switch (comparisonMode) {
            case EQUAL_IGNORE_CASE:
            case NOT_EQUAL_IGNORE_CASE:
                ignoreCase = true;
        }
        List<String> values = new ArrayList<>(propertyValues.size());
        for (String propertyValue : propertyValues) {
            if (ignoreCase) {
                values.add(propertyValue.toLowerCase());
            } else {
                values.add(propertyValue);
            }
        }
        switch (comparisonMode) {
            case EQUAL:
            case EQUAL_IGNORE_CASE:
                Set<String> uniqueValues = new HashSet<>(values);
                return uniqueValues.size() == 1;
            case NOT_EQUAL:
            case NOT_EQUAL_IGNORE_CASE:
                Set<String> allValues = new HashSet<>(values);
                return allValues.size() == values.size();
        }

        return true;
    }
}
