package com.bo.sample.redis.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private long id;
    private String name;
    private int age;
    private String birthDate;
}
