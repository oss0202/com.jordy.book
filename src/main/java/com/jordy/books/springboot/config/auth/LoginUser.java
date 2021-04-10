package com.jordy.books.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 1)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {   // 2)
}
