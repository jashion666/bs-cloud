package org.bs.api.system.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import org.bs.api.system.repository.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wkh
 * @since 2023-01-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("m_role")
public class MRoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色组
     */
    private Integer roleGroupId;

    /**
     * 状态（1正常，0停用）
     */
    private Integer state;

    /**
     * 说明
     */
    private String note;

    /**
     * 排序
     */
    private Integer displayOrder;


}
