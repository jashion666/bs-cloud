package org.bs.common.core.constant;

/**
 * redis key常量 ，和用户有关的redis key，请开头带有 USER: 并带有user id占位符
 *
 * @author :wkh
 */
public class RedisConstant {

    /**
     * 通用过期时间 2小时
     */
    public static final Long NORMAL_EXPIRE_TIME = 7200L;
    /**
     * 菜单
     */
    public static final String MENU_KEY = "SYS:MENU";

    /**
     * 权限菜单
     */
    public static final String AUTH_MENU_KEY = "SYS:AUTH:MENU";

    /**
     * 用户key
     */
    public static final String USER_PATTERN = "USER:{}*";

    /**
     * 用户key
     */
    public static final String USER = "USER:{}";

    /**
     * 该用户的学校id
     */
    public static final String SCH_ID = USER + ":SCH:ID";

    /**
     * 该用户的角色
     */
    public static final String USER_ROLE_KEY = USER + ":ROLE:ID";

    /**
     * 附属信息key
     */
    public static final String APPLY_FOR_KEY = USER + ":APPLY";

    /**
     * 专家登陆
     */
    public static final String REMEMBER_EXPERT_ROUTER = USER + ":EXPERT:URL";

    /**
     * 用户角色key
     */
    public static final String GROUP_ROLE_KEY = "SYS:ROLE:GROUP";

    /**
     * 学校数据
     */
    public static final String SCHOOL_KEY = "SYS:SCHOOL";
    /**
     * 所有的角色
     */
    public static final String ALL_ROLE = "SYS:ROLE:ALL";
    /**
     * 地区数据
     */
    public static final String DIC_ADDRESS_KEY = "SYS:DIC:ADDRESS";

    /**
     * 学科数据
     */
    public static final String DIC_SUBJECT_KEY = "SYS:DIC:SUBJECT";

    /**
     * 学科数据
     */
    public static final String DIC_XUEKEZHUANYE_KEY = "SYS:DIC:XKZY";
    /**
     * 字典其他数据
     */
    public static final String DIC_OTHERS_KEY = "SYS:DIC:OTHERS";

    /**
     * 视频分类
     */
    public static final String VIDEO_CATEGORY_KEY = "SYS:CAT";

    /**
     * 角色组
     */
    public static final String ROLE_GROUP_MEMBER = "ROLE:MEMBER:{}";

    /**
     * redis锁
     */
    public static final String REDIS_LOCK = "SYS:LOCK";

    /**
     * 被评估高校联动下拉框
     */
    public static final String EXPERT_SELECTION = "SYS:EXP:SELECTION";

    /**
     * 审核要素
     */
    public static final String AUDIT_ITEMS = "SYS:AUDIT:ITEM";

    /**
     * 教育行政部门
     */
    public static final String BRANCH = "SYS:BRANCH";

    /**
     * 评估机构
     */
    public static final String AGENCY = "SYS:AGENCY";

    /**
     * 常模类型
     */
    public static final String NORMTYPE = "SYS:NORMTYPE";

    /**
     * 评估机构
     */
    public static final String EVALUATION_AGENCY = "SYS:EXP:AGENCY";

    /**
     * 文件key
     */
    public static final String FILE = "FILE:";

    /**
     * 材料
     */
    public static final String ADMIN_MATERIAL = "FILE:MATERIAL:ADMIN";

    /**
     * 图片key
     */
    public static final String ADMIN_IMG = "FILE:IMG:ADMIN";

    /**
     * 视频key
     */
    public static final String ADMIN_VIDEO = "FILE:VIDEO:ADMIN";

}
