package org.bs.common.core.validator;


import javax.validation.GroupSequence;

/**
 * @author :wkh
 */
@GroupSequence({ValidatorGroup1.class, ValidatorGroup2.class, ValidatorGroup3.class})
public interface UserCheckSequence {
}
