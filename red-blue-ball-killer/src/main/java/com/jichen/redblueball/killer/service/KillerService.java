package com.jichen.redblueball.killer.service;


import java.util.Set;

public interface KillerService<T> {

    Set<Integer> kill(T t);
}
