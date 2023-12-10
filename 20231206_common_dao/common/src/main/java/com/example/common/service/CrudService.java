package com.example.common.service;

import com.example.common.domain.Domain;

import java.util.List;

public interface CrudService<K, D extends Domain<K>, C extends SearchCondition> extends ReadOnlyService<K, D, C> {

    D create(D domain);

    D update(K id, D entity);

    void delete(D entity);

    void deleteById(K id);

    void deleteByIds(List<K> ids);

}
