package com.jichen.redblueball.common.annotations;

import com.jichen.redblueball.common.BallType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Killer {

    String name();

    BallType type();
}
