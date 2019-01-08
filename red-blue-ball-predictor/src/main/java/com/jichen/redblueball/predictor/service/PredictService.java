package com.jichen.redblueball.predictor.service;


import java.util.Set;

public interface PredictService<T> {

    Set<Integer> predict(T t);
}
