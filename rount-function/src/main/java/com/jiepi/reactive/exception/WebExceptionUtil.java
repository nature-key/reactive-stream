package com.jiepi.reactive.exception;

import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Order(-11)
public class WebExceptionUtil implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String message=formatExceptionMassage(throwable);
        DataBuffer wrap = response.bufferFactory().wrap(message.getBytes());


        return response.writeWith(Mono.just(wrap));
    }

    // 格式化异常信息
    private String formatExceptionMassage(Throwable ex) {
        // 发生普通异常后的信息
        String msg = "发生异常：" + ex.getMessage();
        // 发生StudentException后的信息
        if(ex instanceof StudentExcepption) {
            StudentExcepption e = (StudentExcepption) ex;
            msg = msg + "【" + e.getErrField() + ":" + e.getErrValue() + "】";
        }
        return msg;
    }
}
