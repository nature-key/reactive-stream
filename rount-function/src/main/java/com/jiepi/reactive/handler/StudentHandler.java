package com.jiepi.reactive.handler;

import com.jiepi.reactive.bean.Student;
import com.jiepi.reactive.dto.StudentReposiory;
import com.jiepi.reactive.util.StudentUtil;
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
       return mono.flatMap(student -> {
           StudentUtil.validateName(student.getName());
            return  ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(studentReposiory.save(student),Student.class);
        });

    }

    public Mono<ServerResponse> delById(ServerRequest request){
        String id = request.pathVariable("id");
        return studentReposiory.findById(id)
                .flatMap(student ->
                   studentReposiory.delete(student).then(ServerResponse.ok().build())
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateById(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        return studentMono.flatMap(student ->{
            student.setId(id);
            return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentReposiory.save(student),Student.class);
        });

    }





}
