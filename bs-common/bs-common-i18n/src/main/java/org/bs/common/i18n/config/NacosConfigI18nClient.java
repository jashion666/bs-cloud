package org.bs.common.i18n.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.bs.common.i18n.constant.Constants;
import org.bs.common.i18n.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * nacos消息配置类 集成i18n 实现动态国际化语言
 *
 * @author :wkh
 */
@Component
@Slf4j
public class NacosConfigI18nClient {

    @Autowired
    private I18nProperties i18nProperties;

    /**
     * properties缓存
     */
    private final Map<String, Properties> MESSAGE_MAP = new ConcurrentHashMap<>();

    public Properties getMsgProperties(String dataId) {
        // 没取到指定国际化properties则取默认
        return Optional.ofNullable(MESSAGE_MAP.get(dataId)).orElse(MESSAGE_MAP.get(Constants.MESSAGE_DATA_ID_DEFAULT));
    }

    /**
     * 初始化i18n
     */
    @PostConstruct
    public void initI18nMessage() {
        try {
            // 实例化nacos配置
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.SERVER_ADDR, i18nProperties.getServerAddr());
            properties.put(PropertyKeyConst.NAMESPACE, i18nProperties.getNamespace());

            ConfigService configService = NacosFactory.createConfigService(properties);
            List<String> dataIds = i18nProperties.getDataIds();
            for (String dataId : dataIds) {
                //获取配置文件内容
                String config = configService.getConfig(dataId, i18nProperties.getGroup(), 6000);
                // 加载配置
                Properties messages = new Properties();
                messages.load(new StringReader(config));
                // 存入缓存中
                MESSAGE_MAP.put(dataId, messages);
                // 添加配置监听器
                addNacosListener(configService, dataId);
            }
        } catch (NacosException e) {
            log.error("initI18nMessage-NacosException", e);
        } catch (IOException ioException) {
            log.error("messages Exception", ioException);
        }
    }

    /**
     * 添加nacos 配置监听器
     *
     * @param configService nacos配置
     * @param dataId        nacos dataid
     */
    private void addNacosListener(ConfigService configService, String dataId) throws NacosException {
        configService.addListener(dataId, i18nProperties.getGroup(), new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                if (StringUtil.isEmpty(configInfo)) {
                    return;
                }
                try {
                    log.info("nacos版本变更，重新生成message{}", configInfo);
                    Properties properties = MESSAGE_MAP.get(dataId);
                    properties.load(new StringReader(configInfo));
                    MESSAGE_MAP.put(dataId, properties);
                } catch (IOException ioException) {
                    log.error("initI18nMessage reload Exception", ioException);
                }
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }
}
