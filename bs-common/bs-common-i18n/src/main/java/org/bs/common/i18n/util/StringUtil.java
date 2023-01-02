package org.bs.common.i18n.util;


import org.springframework.util.ObjectUtils;

/**
 * 自定义字符串工具类
 *
 * @author :wkh.
 * @date :2020/5/22.
 */
public class StringUtil  {

    /**
     * 字符串是否为空，空的定义如下:<br>
     * 1、为null <br>
     * 2、为""<br>
     *
     * @param str 被检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 替换占位符内容
     *
     * @param content        文本(xxxx{0},{1}xxxx)
     * @param templateParams 替换内容
     * @return String
     */
    public static String formatContent(String content, Object[] templateParams) {
        if (ObjectUtils.isEmpty(templateParams)) {
            return content;
        }
        for (int i = 0, templateParamsLength = templateParams.length; i < templateParamsLength; i++) {
            Object obj = templateParams[i];
            content = content.replace("{" + i + "}", obj.toString());
        }
        return content;
    }
}
