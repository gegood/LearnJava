package com.example.common.service;

import com.example.common.dao.DaoBase;
import com.example.common.domain.Domain;
import com.example.common.exception.AdtsCode;
import com.example.common.exception.AdtsException;
import com.example.common.po.Po;
import com.example.common.service.mapper.DomainMapper;
import com.example.common.service.mapper.DomainMapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

public abstract class ReadOnlyServiceDaoImpl<K, D extends Domain<K>, C extends SearchCondition, P extends Po<K>, R extends DaoBase<P, K>, M extends DomainMapper<D, P>>
        implements ReadOnlyService<K, D, C> {

    @Autowired
    private R dao;

    @Autowired
    private M mapper;

    private DomainMapperWrapper<D, P, M> mapperWrapper;

    @PostConstruct
    public void afterPropertiesSet() {
        mapperWrapper = new DomainMapperWrapper<>(mapper);
    }

    @Override
    @Transactional(readOnly = true)
    public D get(K id) {
        return mapper().toDO(dao().findById(id).orElseThrow(() -> new AdtsException(AdtsCode.NOT_FOUND, "couldn't find '" + id + "'")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<D> getByIds(List<K> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return mapper().toDO(dao().findAllById(ids));
    }

    @Override
    @Transactional(readOnly = true)
    public List<D> list(C condition) {
        List<P> pos = dao().findAll(toSpec(condition));
        return mapper().toDO(pos);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<D> page(C condition, Integer page, Integer size) {
        Pageable pageable = page(page, size);
        Page<P> pagePO = dao().findAll(toSpec(condition), pageable);
        return mapper().toDO(pagePO);
    }

    @Override
    public long count(C condition) {
        return dao().count(toSpec(condition));
    }

    protected Specification<P> toSpec(C condition) {
        return null;
    }

    protected Pageable page(Integer page, Integer size) {
        return SearchHelper.page(page, size);
    }

    protected String addWildcard(String keyword) {
        return SearchHelper.addWildcard(keyword);
    }

    protected R dao() {
        return dao;
    }

    protected DomainMapperWrapper<D, P, M> mapper() {
        return mapperWrapper;
    }

}
