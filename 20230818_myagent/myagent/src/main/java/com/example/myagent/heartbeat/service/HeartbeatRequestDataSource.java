package com.example.myagent.heartbeat.service;

import com.example.common.heartbeat.HeartbeatRequest;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

public interface HeartbeatRequestDataSource {

    /**
     * 收集信息
     * @param request 当前请求
     */
    void collectHeartbeatData(HeartbeatRequest request);

}
