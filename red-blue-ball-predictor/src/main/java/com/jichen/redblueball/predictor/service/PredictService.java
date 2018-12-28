package com.jichen.redblueball.predictor.service;


import com.jichen.redblueball.model.Ball;

import java.util.Set;

public interface PredictService<T> {

    Set<Ball> predict(T t);
}
