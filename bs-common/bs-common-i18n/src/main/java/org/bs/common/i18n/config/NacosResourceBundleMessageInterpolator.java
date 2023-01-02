package org.bs.common.i18n.config;

import org.bs.common.i18n.constant.Constants;
import org.hibernate.validator.messageinterpolation.AbstractMessageInterpolator;

import java.util.Locale;

/**
 * 自定义消息转换器
 * @author :wkh
 */
public class NacosResourceBundleMessageInterpolator extends AbstractMessageInterpolator {

    private final NacosI18nMessageSource messageSource;

    public NacosResourceBundleMessageInterpolator(NacosI18nMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected String interpolate(Context context, Locale locale, String term) {

        if (!term.startsWith(Constants.LEFT_BRACE) && !term.endsWith(Constants.RIGHT_BRACE)) {
            return term;
        }

        // 去除开头结尾大括号
        term = term.substring(1);
        term = term.substring(0, term.length() - 1);
        // 没有参数，直接返回message数据
        if (!term.contains(Constants.SEMICOLON)) {
            return messageSource.getMessage(term);
        }

        // 按照冒号分割， 冒号后面为参数
        String[] split = term.split(Constants.SEMICOLON);
        String key = split[0];
        String arg = split[1];
        String[] args = arg.split(Constants.COMMA);
        // 取出message
        return messageSource.getMessage(key, args, Locale.getDefault());
    }

}
