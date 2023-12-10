package com.example.myagent.heartbeat.service;

import com.example.common.heartbeat.HeartbeatRequest;
import com.example.common.heartbeat.data.Agent;
import com.example.common.heartbeat.data.AgentStatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Component
public class AgentDataSource implements HeartbeatRequestDataSource {

    @Value("${server.port}")
    private Integer port;

    @Override
    public void collectHeartbeatData(HeartbeatRequest request) {
        Agent agent = new Agent();
        agent.setAgentId(11111L);
        agent.setName("agent-wuzhihao");
        agent.setPort(port);
        agent.setStatus(AgentStatusEnum.REGISTERED.getValue());
        agent.setReportTime(System.currentTimeMillis());
        request.setAgent(agent);
    }
}
