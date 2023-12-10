package com.example.myconsole.heartbeat.service.mapper;

import com.example.common.service.mapper.CycleAvoidingMappingContext;
import com.example.common.service.mapper.DateMapper;
import com.example.common.service.mapper.DomainMapper;
import com.example.myconsole.heartbeat.domain.AgentDo;
import com.example.myconsole.heartbeat.po.AgentPo;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { DateMapper.class }, builder = @org.mapstruct.Builder(disableBuilder = true))
public interface AgentMapper extends DomainMapper<AgentDo, AgentPo> {

    @Override
    @Mapping(target = "deleted", ignore = true)
    AgentPo toPO(AgentDo domain, @Context CycleAvoidingMappingContext context);

    @Override
    AgentDo toDO(AgentPo po, @Context CycleAvoidingMappingContext context);
}
