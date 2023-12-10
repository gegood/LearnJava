package com.example.common.service.mapper;

import com.example.common.service.PageResult;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

public class DomainMapperWrapper<D, P, M extends DomainMapper<D, P>> {

    private M mapper;

    public DomainMapperWrapper(M mapper) {
        this.mapper = mapper;
    }

    public P toPO(D domain) {
        return mapper.toPO(domain, CycleAvoidingMappingContext.create());
    }

    public D toDO(P po) {
        return mapper.toDO(po, CycleAvoidingMappingContext.create());
    }

    public void updatePO(D domain, @MappingTarget P po) {
        mapper.updatePO(domain, po);
    }

    public void updateDomain(P po, @MappingTarget D domain) {
        mapper.updateDomain(po, domain);
    }

    public List<D> toDO(List<P> po) {
        return mapper.toDO(po, CycleAvoidingMappingContext.create());
    }

    public PageResult<D> toDO(Page<P> po) {
        return mapper.toDO(po);
    }

    public M unwrap() {
        return mapper;
    }

}
