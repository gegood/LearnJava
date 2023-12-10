package com.example.myagent.heartbeat.controller;

import com.example.common.heartbeat.HeartbeatRequest;
import com.example.common.heartbeat.HeartbeatResponse;
import com.example.common.heartbeat.data.AgentStatusEnum;
import com.example.myagent.heartbeat.client.MyFeignClient;
import com.example.myagent.heartbeat.service.HeartbeatRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
    @Qualifier("MyFeignClient")
    @Autowired
    MyFeignClient myFeignClient;
    @Autowired
    HeartbeatRequestService heartbeatService;

    // http://127.0.0.1:8081/heartbeat
    @RequestMapping("/heartbeat")
    @ResponseBody
    public HeartbeatResponse heartbeat() {

        HeartbeatRequest heartbeatRequest = heartbeatService.collectHeartbeatRequest();
        HeartbeatResponse response = myFeignClient.sendHeartbeat(heartbeatRequest);

        log.info("response = {}, status = {}", response, Objects.requireNonNull(AgentStatusEnum.getEnumByValue(response.getConsole().getStatus())).getDescription());

        return response;
    }
}
