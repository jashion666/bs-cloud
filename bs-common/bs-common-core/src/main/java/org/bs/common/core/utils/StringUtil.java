package org.bs.common.core.utils;



import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 自定义字符串工具类
 *
 * @author :wkh.
 * @date :2020/5/22.
 */
public class StringUtil extends StrUtil {

    /**
     * 分转为元
     *
     * @param fee 分
     * @return 元
     */
    public static String feeToYuan(String fee) {
        return new BigDecimal(fee).divide(new BigDecimal(100), 2, RoundingMode.FLOOR).toString();
    }


    /**
     * 补充字符串以满足最小长度
     *
     * <pre>
     * StrUtil.padPre(null, *, *);//null
     * StrUtil.padPre("1", 3, '0');//"001"
     * StrUtil.padPre("123", 2, '0');//"123"
     * </pre>
     *
     * @param str       字符串
     * @param minLength 最小长度
     * @param padChar   补充的字符
     * @return 补充后的字符串
     */
    public static String padPreWithLength(CharSequence str, int minLength, char padChar) {
        if (str.length() >= minLength) {
            return str.toString();
        }
        return padPre(str, minLength, padChar);
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 补充后的字符串
     */
    public static String firstToUpperCase(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 取得文件后缀(带有.)
     *
     * @param filename 文件名
     * @return 文件后缀
     */
    public static String getFileType(String filename) {
        if (isEmpty(filename)) {
            return "";
        }
        //带扩展名的文件名
        String[] temp = filename.split("\\.");
        return "." + temp[temp.length - 1];
    }

    /**
     * 截取字符串到最大位数
     *
     * @param content   内容
     * @param maxLength 最大长度
     * @return 截取后的字符串
     */
    public static String subMaxLength(String content, Integer maxLength) {
        if (StringUtil.isEmpty(content)) {
            return content;
        }

        if (content.length() > maxLength) {
            return content.substring(0, maxLength);
        }

        return content;
    }

    /**
     * 替换占位符内容
     *
     * @param content        文本(xxxx{},{}xxxx)
     * @param templateParams 替换内容
     * @return String
     */
    public static String replaceContent(String content, List<String> templateParams) {
        for (String string : templateParams) {
            content = StringUtil.format(content, string);
        }
        return content;
    }

    /**
     * 替换占位符内容
     *
     * @param content        文本(xxxx{0},{1}xxxx)
     * @param templateParams 替换内容
     * @return String
     */
    public static String formatContent(String content, Object[] templateParams) {
        if (ObjectUtil.isEmpty(templateParams)) {
            return content;
        }
        for (int i = 0, templateParamsLength = templateParams.length; i < templateParamsLength; i++) {
            Object obj = templateParams[i];
            content = content.replace("{" + i + "}", obj.toString());
        }
        return content;
    }
}
