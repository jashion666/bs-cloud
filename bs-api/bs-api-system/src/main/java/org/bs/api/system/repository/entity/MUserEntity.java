package org.bs.api.system.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("m_user")
public class MUserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 索引id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 随机加密码10位
     */
    private String salt;

    /**
     * 是否为管理员
     */
    private Integer admin;

    /**
     * 角色
     */
    private String role;

    /**
     * 性别(1:男,2：女)
     */
    private Integer sex;

    /**
     * 出生年月日（十位时间戳）
     */
    private Integer birth;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * 电话
     */
    private String phone;

    /**
     * 住址（省市区，用逗号分割）
     */
    private String area;

    /**
     * 详细住址
     */
    private String address;

    /**
     * 专业技能
     */
    private String skill;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 0：正常,1：锁定
     */
    private Integer locked;


}
