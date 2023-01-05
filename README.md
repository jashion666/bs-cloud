<h1 style="margin: 30px 0 30px; font-weight: bold;text-align: center">基础开发框架 - spring-cloud微服务版本</h1>

## 简介

该项目是一套前后端分离基础微服务开发框架，适用于中型项目。

### 系统模块

    bs-cloud    
    ├── bs-ui                          // 前端框架 [4200]
    ├── bs-gateway                     // 网关模块 [8081]
    ├── bs-auth                        // 认证中心 [8082]
    ├── bs-api                         // 接口模块
    │       └── bs-api-system          // 提供bs-system feign接口
    ├── bs-common                      // 通用模块
    │       └── bs-common-core         // 核心模块
    │       └── bs-common-generator    // 代码生成模块
    │       └── bs-common-i18n         // 国际化i18n模块
    │       └── bs-common-redis        // 缓存服务
    │       └── bs-common-satoken      // 鉴权模块
    │       └── bs-common-security     // 安全模块
    ├── bs-modules                     // 业务模块
    │       └── bs-system              // 系统模块 [8083]
    ├── bs-stream-kafka                // mq模块（kafka）
    │       └── bs-kafka-consumer      // 消费者[8085]
    │       └── bs-kafka-producer      // 消息提供者
    ├── bs-visual
    │       └── bs-monitor             // 系统监控 [8084]
    ├──pom.xml                         // 公共依赖

### 系统架构图
<img src="bs-doc/image/bs-cloud-framework.png">

