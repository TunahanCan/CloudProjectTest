package com.example.studentservice.security;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRoles {

    String[] hasRoles() default "Set has role";

}
