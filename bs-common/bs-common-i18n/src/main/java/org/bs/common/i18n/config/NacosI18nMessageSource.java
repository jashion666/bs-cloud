package org.bs.common.i18n.config;

import org.bs.common.i18n.constant.Constants;
import org.bs.common.i18n.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Properties;

/**
 * nacos 消息类
 *
 * @author :wkh
 */
@Component
public class NacosI18nMessageSource implements MessageSource {

    @Autowired
    private NacosConfigI18nClient nacosConfigI18nClient;

    private static final String MESSAGE_DATA_ID_FORMAT = "messages_{}.properties";

    /**
     * 读取message
     *
     * @param key  k
     * @param args 占位参数
     * @return String
     */
    public String getMessage(String key, Object... args) {
        String language = "";
//        Locale locale = LocaleContextHolder.getLocale();
//        String language = locale.getLanguage();
//        if (StringUtil.isEmpty(language)) {
//            language = "zh";
//        }
        String msg = "";
        if (StringUtil.isEmpty(key)) {
            return msg;
        }
        String dataId = Constants.MESSAGE_DATA_ID_DEFAULT;
        if (!StringUtil.isEmpty(language)) {
            // 转换国际化message
            dataId = MESSAGE_DATA_ID_FORMAT.replace("{}", language);
        }
        Properties msgProperties = nacosConfigI18nClient.getMsgProperties(dataId);
        // 获取消息
        msg = msgProperties.getProperty(key);
        // 占位符替换
        return StringUtil.formatContent(msg, args);
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return getMessage(code, args);
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return getMessage(code, args);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }
}
