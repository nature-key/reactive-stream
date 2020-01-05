package com.jiepi.reactive.router;


import com.jiepi.reactive.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> customerRouter(StudentHandler handler){

        return RouterFunctions
                .nest(RequestPredicates.path("/student"),
                        RouterFunctions.route(RequestPredicates.GET("/all"),handler::findAll)
                                .andRoute(RequestPredicates.POST("/save")
                          .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),handler::save));

    }
}
