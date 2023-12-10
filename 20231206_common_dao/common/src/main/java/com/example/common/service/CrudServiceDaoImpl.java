package com.example.common.service;

import com.example.common.dao.DaoBase;
import com.example.common.domain.Domain;
import com.example.common.exception.AdtsCode;
import com.example.common.exception.AdtsException;
import com.example.common.id.IdGenerator;
import com.example.common.po.Po;
import com.example.common.service.mapper.DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class CrudServiceDaoImpl<K, D extends Domain<K>, C extends SearchCondition, P extends Po<K>, R extends DaoBase<P, K>, M extends DomainMapper<D, P>>
        extends ReadOnlyServiceDaoImpl<K, D, C, P, R, M> implements CrudService<K, D, C> {

    @Autowired(required = false)
    private IdGenerator<K> idGenerator;

    @Override
    @Transactional
    public D create(D domain) {
        P po = dao().save(mapper().toPO(domain));
        return mapper().toDO(dao().findById(po.getId()).orElse(null));
    }

    @Override
    @Transactional
    public D update(K id, D domain) {
        P po = dao().findById(id).orElseThrow(() -> new AdtsException(AdtsCode.NOT_FOUND));
        mapper().updatePO(domain, po);
        po = dao().save(po);
        return mapper().toDO(po);
    }

    @Override
    @Transactional
    public void delete(D domain) {
        deleteById(domain.getId());
    }

    @Override
    @Transactional
    public void deleteById(K id) {
        dao().deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByIds(List<K> ids) {
        dao().deleteAllById(ids);
    }

    protected IdGenerator<K> idGenerator() {
        return idGenerator;
    }

}
