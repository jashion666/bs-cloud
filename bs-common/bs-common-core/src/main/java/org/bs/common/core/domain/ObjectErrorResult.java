package org.bs.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author :wkh
 */
@Data
@AllArgsConstructor
public class ObjectErrorResult {

    private String name;

    protected String message;
}
