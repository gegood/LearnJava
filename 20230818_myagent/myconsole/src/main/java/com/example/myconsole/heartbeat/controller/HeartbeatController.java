package com.example.myconsole.heartbeat.controller;

import com.example.common.heartbeat.HeartbeatRequest;
import com.example.common.heartbeat.HeartbeatResponse;
import com.example.common.heartbeat.data.AgentStatusEnum;
import com.example.myconsole.dao.service.AgentService;
import com.example.myconsole.heartbeat.service.HeartbeatResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Slf4j
@Controller
public class HeartbeatController {

    @Autowired
    HeartbeatResponseService heartbeatService;
    @Autowired
    AgentService agentService;

    // http://127.0.0.1:8080/heartbeat
    @RequestMapping("/heartbeat")
    @ResponseBody
    public HeartbeatResponse heartbeatConsole(@RequestBody HeartbeatRequest body) {

        log.info("request = {}, status = {}", body, Objects.requireNonNull(AgentStatusEnum.getEnumByValue(body.getAgent().getStatus())).getDescription());

        agentService.save(body.getAgent());

        return heartbeatService.collectHeartbeatResponse();
    }

}
