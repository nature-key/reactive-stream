package com.jiepi.reactive.handler;

import com.jiepi.reactive.bean.Student;
import com.jiepi.reactive.dto.StudentReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {

    @Autowired
    private StudentReposiory studentReposiory;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentReposiory.findAll(), Student.class);
    }


    public  Mono<ServerResponse> save(ServerRequest request){
        Mono<Student> mono = request.bodyToMono(Student.class);
        return  ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentReposiory.saveAll(mono),Student.class);
    }



}
