package org.bs.common.i18n.config;

import org.bs.common.i18n.util.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 语言拦截器
 *
 * @author wkh
 */
public class LocalInterceptor implements HandlerInterceptor {

    @Autowired
    private LanguageUtils languageUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取语言信息
        String language = request.getHeader("Accept-Language");
        //设置语言环境
        languageUtils.setLanguage(language);
        return true;
    }

}
