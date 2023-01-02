package org.bs.common.i18n.util;

import com.alibaba.nacos.common.utils.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 设置语言相关工具类
 */
@Component
public class LanguageUtils {

    public void setLanguage(String language) {
        if (!StringUtils.isNotBlank(language)) {
            return;
        }

        if (language.indexOf("zh-hant-hk") == 0 || language.indexOf("zh-hant-HK") == 0) {
            //香港繁体
            LocaleContextHolder.setLocale(new Locale("zh-hant-hk", "CN"));
        } else if (language.indexOf("hant") == 0 || language.indexOf("Hant") == 0) {
            //繁体
            LocaleContextHolder.setLocale(new Locale("hant", "CN"));
        } else if (language.indexOf("en-uk") == 0) {
            //英国英语
            LocaleContextHolder.setLocale(new Locale("en-uk", "GB"));
        } else if (language.indexOf("en-au") == 0) {
            //澳大利亚英语
            LocaleContextHolder.setLocale(new Locale("en-au", "AU"));
        } else if (language.indexOf("en") == 0) {
            //英语
            LocaleContextHolder.setLocale(new Locale("en", "US"));
        } else if (language.indexOf("zh") == 0) {
            //简体中文
            LocaleContextHolder.setLocale(new Locale("zh", "CN"));
        } else if (language.indexOf("ms") == 0) {
            //马来西亚
            LocaleContextHolder.setLocale(new Locale("ms", "MY"));
        } else if (language.indexOf("th") == 0) {
            //泰语
            LocaleContextHolder.setLocale(new Locale("th", "TH"));
        } else if (language.indexOf("nl") == 0) {
            //荷兰语
            LocaleContextHolder.setLocale(new Locale("nl", "NL"));
        } else if (language.indexOf("ko") == 0) {
            //韩语
            LocaleContextHolder.setLocale(new Locale("ko", "KR"));
        } else if (language.indexOf("fr-ch") == 0) {
            //瑞士法语
            LocaleContextHolder.setLocale(new Locale("fr-ch", "CH"));
        } else if (language.indexOf("fr") == 0) {
            //法语
            LocaleContextHolder.setLocale(new Locale("fr", "FR"));
        } else if (language.indexOf("de-ch") == 0) {
            //瑞士德语
            LocaleContextHolder.setLocale(new Locale("de-ch", "CH"));
        } else if (language.indexOf("de") == 0) {
            //德语
            LocaleContextHolder.setLocale(new Locale("de", "DE"));
        } else if (language.indexOf("it") == 0) {
            //意大利语
            LocaleContextHolder.setLocale(new Locale("it", "IT"));
        } else if (language.indexOf("span") == 0) {
            //西班牙语
            LocaleContextHolder.setLocale(new Locale("span", "SPAN"));
        } else if (language.indexOf("id") == 0) {
            //印度尼西亚语,由于spring底层将id转化为了in,对应的语言配置文件为messages_in.properties
            LocaleContextHolder.setLocale(new Locale("id", "ID"));
        } else if (language.indexOf("ja") == 0) {
            //印度尼西亚语,由于spring底层将id转化为了in,对应的语言配置文件为messages_in.properties
            LocaleContextHolder.setLocale(Locale.JAPAN);
        }

    }
}
