package com.jiepi.reactive.handler;

import com.jiepi.reactive.bean.Student;
import com.jiepi.reactive.dto.StudentReposiory;
import com.jiepi.reactive.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.RecursiveTask;

@RestController
public class StudentHandler {

    @Autowired
    StudentReposiory reposiory;

    @GetMapping("/student/{id}")
    public Mono<Student> findById(@PathVariable String id) {
        Mono<Student> byId = reposiory.findById(id);
        return byId;

    }


    @GetMapping("/student")
    public Flux<Student> findAll(){
        return  reposiory.findAll();
    }


    @PostMapping("/student")
    public Mono<Student> save(@Valid @RequestBody Student student) {
        StudentUtil.validateName(student.getName());
        Mono<Student> save = reposiory.save(student);
        return save;
    }


    @PutMapping("/student/{id}")
    public Mono<ResponseEntity<Student>> updateById(@PathVariable String id
            , @RequestBody Student student) {
        return reposiory.findById(id).flatMap(stu -> {
            stu.setAge(student.getAge());
            stu.setName(student.getName());
            return reposiory.save(stu);
        }).map(stu -> new ResponseEntity<Student>(stu,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/student/{id}")
    public  Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") String id){
        return reposiory.findById(id).flatMap(stu->reposiory.delete(stu))
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/student/del/{id}")
    public Mono<Void> delete(@PathVariable("id") String id){
        return  reposiory.deleteById(id);
    }


    @GetMapping("/student/{start}/{end}")
    public  Flux<Student> findAge(@PathVariable("start") int start
    ,@PathVariable("end") int end){
        return  reposiory.findByAgeBetween(start,end);
    }

    @GetMapping(value = "/sse/student/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public  Flux<Student> findAgeSSE(@PathVariable("start") int start
            ,@PathVariable("end") int end){
        return  reposiory.findByAgeBetween(start,end);
    }

}
