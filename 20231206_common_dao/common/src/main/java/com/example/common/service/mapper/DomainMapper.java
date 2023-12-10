package com.example.common.service.mapper;

import com.example.common.service.PageResult;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DomainMapper<D, P> {

    P toPO(D domain, @Context CycleAvoidingMappingContext context);

    D toDO(P po, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "id", ignore = true)
    void updatePO(D domain, @MappingTarget P po);

    void updateDomain(P po, @MappingTarget D domain);

    List<D> toDO(List<P> po, @Context CycleAvoidingMappingContext context);

    default PageResult<D> toDO(Page<P> po) {
        return new PageResult<>(po.getTotalElements(), po.getPageable().getPageNumber() + 1, po.getSize(),
                toDO(po.getContent(), new CycleAvoidingMappingContext()));
    }

}
