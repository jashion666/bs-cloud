#java 版本
FROM java:8
##挂载的docker卷
VOLUME /tmp
MAINTAINER wkh
#前者是要操作的jar包  后者自定义jar包名
ADD jar/*.jar sentinel-dashboard.jar
#定义时区参数
ENV TZ=Asia/Shanghai
#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone
#配置启动命令,-D表示设置JVM参数
ENTRYPOINT ["java","-jar","-Dserver.port=8858","-Dcsp.sentinel.dashboard.server=localhost:8858","-Dproject.name=sentinel-dashboard","/sentinel-dashboard.jar"]
