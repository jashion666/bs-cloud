<h1 style="margin: 30px 0 30px; font-weight: bold;text-align: center">基础开发框架 - spring-cloud微服务版本</h1>

## 简介

该项目是一套前后端分离基础微服务开发框架，适用于中型项目。

### 系统模块

    bs-cloud    
    ├── bs-ui                                          // 前端框架 [4200]
    ├── bs-gateway                                     // 网关模块 [8081]
    ├── bs-auth                                        // 认证中心 [8082]
    ├── bs-api                                         // 接口模块
    │       └── bs-api-system                          // 系统接口
    ├── bs-common                                      // 通用模块
    │       └── bs-common-core                         // 核心模块
    │       └── bs-common-datascope                    // 权限范围
    │       └── bs-common-datasource                   // 多数据源
    │       └── bs-common-log                          // 日志记录
    │       └── bs-common-redis                        // 缓存服务
    │       └── bs-common-security                     // 安全模块
    │       └── bs-common-swagger                      // 系统接口
    ├── bs-modules                                     // 业务模块
    │       └── bs-system                              // 系统模块 [8083]
    │       └── bs-file                                // 文件服务 [8084]
    ├──pom.xml                                         // 公共依赖

### 系统功能

1. 服务网关
2. 认证中心
3. 注册中心
4. 配置中心
5. 服务调用
6. 服务监控
7. 系统接口
8. 连读追踪
9. 熔断降级


