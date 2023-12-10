package com.example.myconsole.heartbeat.service;

import com.example.common.heartbeat.data.Agent;
import com.example.common.service.CrudService;
import com.example.myconsole.heartbeat.domain.AgentDo;

public interface AgentService extends CrudService<Long, AgentDo, AgentSearchCondition> {

    void handleHeartbeat(Agent agent);

    void save(AgentDo agentDo);
}
