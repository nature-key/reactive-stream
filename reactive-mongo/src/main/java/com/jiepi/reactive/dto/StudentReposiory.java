package com.jiepi.reactive.dto;

import com.jiepi.reactive.bean.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface StudentReposiory  extends ReactiveMongoRepository<Student,String> {


    Flux<Student> findByAgeBetween(int start,int end);

    @Query("{'age':{'$gte':?0,'$lt':?1}}")
    Flux<Student> queryByAge(int start,int end);
}
