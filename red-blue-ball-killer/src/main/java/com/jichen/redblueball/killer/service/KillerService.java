package com.jichen.redblueball.killer.service;


import com.jichen.redblueball.common.model.History;

import java.util.Set;

public interface KillerService {

    Set<Integer> kill(History history);
}
