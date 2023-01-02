package org.bs.api.system.repository.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author :wkh
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * delete time
     */
    private Integer deletedAt;

    /**
     * create time
     */
    private Integer createdAt;

    /**
     * update time
     */
    private Integer updatedAt;
}
