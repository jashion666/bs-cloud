package org.bs.common.core.utils;

import org.bs.common.core.domain.AjaxResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;


/**
 * 客户端工具类
 *
 * @author wkh
 */
public class ServletUtils {

    /**
     * 设置webflux模型响应
     *
     * @param response ServerHttpResponse
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, Object value) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(com.alibaba.fastjson.JSON.toJSONString(AjaxResult.fail(value.toString())).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}