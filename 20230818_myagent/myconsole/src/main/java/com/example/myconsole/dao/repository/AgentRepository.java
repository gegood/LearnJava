package com.example.myconsole.dao.repository;

import com.example.myconsole.dao.entity.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    AgentEntity findAgentEntityByAgentId(Long agentId);
}
