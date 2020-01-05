package com.jiepi.reactive.bean;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Student {

    @Id
    private String id;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Range(min = 10, max = 50, message = "年龄必须在{min}-{max}范围")
    private int age;
}
