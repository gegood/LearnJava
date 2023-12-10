package com.example.myconsole.heartbeat.dao;

import com.example.common.dao.DaoBase;
import com.example.myconsole.heartbeat.po.AgentPo;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentDao extends DaoBase<AgentPo, Long> {

    AgentPo findAgentPoByAgentId(Long agentId);
}
