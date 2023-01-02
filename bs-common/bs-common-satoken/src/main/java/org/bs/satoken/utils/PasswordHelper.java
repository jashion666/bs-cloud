package org.bs.satoken.utils;


import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * 密码生成器
 *
 * @author :wkh
 */
public class PasswordHelper {

    /**
     * 加密方式
     */
    private static final String ALGORITHM_NAME = "md5";
    /**
     * 加密次数
     */
    private static final int HASH_ITERATIONS = 2;

    private static final String BASE_SALT = "bakfbaowbfjkabofbaCCUVUIVIWDKV%$&^%*&*%$1131415=/";

    /**
     * 盐长度
     */
    private static final int SALT_LENGTH = 5;

    public static void main(String[] args) {
        String salt = RandomUtil.randomString(BASE_SALT, 16);
        System.out.println("盐: " + salt);
        System.out.println("密码: " + encryptPassword("123456", salt));
    }


    /**
     * md5 encrypt password
     *
     * @param password 密码
     * @param salt     盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt) {
        return SaSecureUtil.md5BySalt(password, salt);
    }


    /**
     * 比较密码是否一致
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param salt        盐
     * @return 是否一致
     */
    public static boolean matchesPassword(String oldPassword, String newPassword, String salt) {
        newPassword = encryptPassword(newPassword, salt);
        return oldPassword.equals(newPassword);
    }

    /**
     * 生成盐
     *
     * @return 盐
     */
    public static String getSalt() {
        return BCrypt.gensalt(SALT_LENGTH).toString();
    }
}
