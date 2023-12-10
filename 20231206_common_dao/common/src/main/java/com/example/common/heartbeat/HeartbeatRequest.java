package com.example.common.heartbeat;

import com.example.common.heartbeat.data.Agent;
import lombok.Data;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Data
public class HeartbeatRequest {

    /**
     * 请求id
     */
    private Long reqId;

    private Agent agent;

}
