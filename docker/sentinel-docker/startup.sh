#!/bin/sh

#使用sentinel.yml方式
#docker-compose -f sentinel.yml up -d

# 构建镜像
docker build -t sentinel-server .
# 运行
docker run --name sentinel-dashboard-1.8.5 --restart=always --privileged=true -p 8858:8858 -d sentinel-server

# 启动后访问
# http://localhost:8858/#/login
