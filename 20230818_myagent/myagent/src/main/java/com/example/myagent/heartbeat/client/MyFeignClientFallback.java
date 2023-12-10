package com.example.myagent.heartbeat.client;

import com.example.common.heartbeat.HeartbeatRequest;
import com.example.common.heartbeat.HeartbeatResponse;
import com.example.common.heartbeat.data.Console;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Slf4j
@Component("MyFeignClientFallback")
@RequestMapping("/fallback")
public class MyFeignClientFallback implements MyFeignClient {

    @Override
    public HeartbeatResponse sendHeartbeat(HeartbeatRequest body) {
        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        heartbeatResponse.setConsole(new Console());
        return heartbeatResponse;
    }
}
