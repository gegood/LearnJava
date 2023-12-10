package com.example.common.service;

import com.example.common.domain.Domain;

import java.util.List;

public interface ReadOnlyService<K, D extends Domain<K>, C extends SearchCondition> extends Service {

    D get(K id);

    List<D> getByIds(List<K> ids);

    List<D> list(C condition);

    PageResult<D> page(C condition, Integer page, Integer size);

    long count(C condition);

}
