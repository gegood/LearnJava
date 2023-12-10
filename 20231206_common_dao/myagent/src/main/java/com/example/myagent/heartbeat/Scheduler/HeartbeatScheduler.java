package com.example.myagent.heartbeat.Scheduler;

import com.example.common.heartbeat.HeartbeatRequest;
import com.example.common.heartbeat.HeartbeatResponse;
import com.example.common.heartbeat.data.AgentStatusEnum;
import com.example.myagent.common.CommonScheduler;
import com.example.myagent.heartbeat.client.MyFeignClient;
import com.example.myagent.heartbeat.service.HeartbeatRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Slf4j
@Component
public class HeartbeatScheduler {
    @Value("${heartbeat.check-interval:1}")
    private Long heartbeatCheckInterval;
    @Qualifier("MyFeignClient")
    @Autowired
    MyFeignClient myFeignClient;
    @Autowired
    private CommonScheduler commonScheduler;
    @Autowired
    HeartbeatRequestService heartbeatService;

    @PostConstruct
    public void init(){
        commonScheduler.get().scheduleWithFixedDelay(
                this::run, heartbeatCheckInterval, heartbeatCheckInterval, TimeUnit.SECONDS);
    }

    private void run(){
        HeartbeatRequest heartbeatRequest = heartbeatService.collectHeartbeatRequest();
        HeartbeatResponse response = myFeignClient.sendHeartbeat(heartbeatRequest);

        log.info("response = {}, status = {}", response, Objects.requireNonNull(AgentStatusEnum.getEnumByValue(response.getConsole().getStatus())).getDescription());

    }

}
