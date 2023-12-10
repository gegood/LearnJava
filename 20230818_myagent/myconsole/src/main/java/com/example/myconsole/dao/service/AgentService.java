package com.example.myconsole.dao.service;

import com.example.common.heartbeat.data.Agent;
import com.example.myconsole.dao.entity.AgentEntity;
import com.example.myconsole.dao.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    @Autowired
    AgentRepository agentRepository;

    public void save(Agent agent){

        AgentEntity agentEntity = agentRepository.findAgentEntityByAgentId(agent.getAgentId());
        if(agentEntity == null){
            agentEntity = new AgentEntity();
            agentEntity.setAgentId(agent.getAgentId());
            agentEntity.setName(agent.getName());
            agentEntity.setPort(agent.getPort());
            agentEntity.setStatus(agent.getStatus());
            agentEntity.setReportTime(agent.getReportTime());
        }
        else{
            agentEntity.setAgentId(agent.getAgentId());
            agentEntity.setName(agent.getName());
            agentEntity.setPort(agent.getPort());
            agentEntity.setStatus(agent.getStatus());
            agentEntity.setReportTime(agent.getReportTime());
        }

        agentRepository.save(agentEntity);
    }

}
