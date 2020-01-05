package com.jiepi.reactive.util;

import com.jiepi.reactive.exception.StudentExcepption;

import java.util.stream.Stream;

public class StudentUtil {

  private static  final String[] INVALIED_NAMES={"admin","administrator"};

    public  static  void  validateName(String name){
        Stream.of(INVALIED_NAMES)
                .filter(invalied_name->invalied_name.equalsIgnoreCase(name))
                .findAny()
                .ifPresent(in->{
                    throw  new StudentExcepption("name",in,"使用了非法名称");
                });

    }
}
