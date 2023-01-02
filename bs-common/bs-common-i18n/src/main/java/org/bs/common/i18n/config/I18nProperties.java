package org.bs.common.i18n.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wkh
 */
@Component
@RefreshScope
@Data
public class I18nProperties {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;

    @Value(value = "${i18n.group}")
    private String group;

    @Value(value = "${i18n.namespace}")
    private String namespace;

    @Value(value = "${i18n.data-ids}")
    private List<String> dataIds = new ArrayList<>();
}
