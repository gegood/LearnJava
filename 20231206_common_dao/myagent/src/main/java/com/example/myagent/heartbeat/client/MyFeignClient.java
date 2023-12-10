package com.example.myagent.heartbeat.client;

import com.example.common.heartbeat.HeartbeatRequest;
import com.example.common.heartbeat.HeartbeatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuzhihao
 * @date 2023/08/21
 */

@FeignClient(
        name = "${heartbeat.client-name}",
        url = "${heartbeat.console-url}",
        fallback = MyFeignClientFallback.class)
@Qualifier("MyFeignClient")
public interface MyFeignClient {

    @RequestMapping(value = "/heartbeat")
    HeartbeatResponse sendHeartbeat(@RequestBody HeartbeatRequest body);

}
