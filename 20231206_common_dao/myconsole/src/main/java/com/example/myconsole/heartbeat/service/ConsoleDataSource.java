package com.example.myconsole.heartbeat.service;

import com.example.common.heartbeat.HeartbeatResponse;
import com.example.common.heartbeat.data.AgentStatusEnum;
import com.example.common.heartbeat.data.Console;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsoleDataSource implements HeartbeatResponseDataSource {

    @Value("${server.port}")
    private Integer port;

    @Override
    public void collectHeartbeatData(HeartbeatResponse response) {
        Console console = new Console();
        console.setPort(port);
        console.setName("myconsole");
        console.setStatus(AgentStatusEnum.REGISTERED.getValue());
        console.setSource("server");
        response.setConsole(console);
    }
}
