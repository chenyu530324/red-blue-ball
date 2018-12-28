package com.jichen.redblueball.killer.service;


import com.jichen.redblueball.model.Ball;

import java.util.Set;

public interface KillerService<T> {

    Set<Ball> kill(T t);
}
