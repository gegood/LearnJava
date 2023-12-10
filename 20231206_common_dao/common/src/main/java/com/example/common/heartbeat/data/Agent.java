package com.example.common.heartbeat.data;

import lombok.Data;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Data
public class Agent {

    private Long agentId;

    private String name;

    private Integer port;

    private Integer status;

    private Long reportTime;
}
