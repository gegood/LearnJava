package com.example.myconsole.heartbeat.service;

import com.example.common.heartbeat.HeartbeatResponse;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

public interface HeartbeatResponseDataSource {

    /**
     * 收集信息
     * @param response 当前回复的数据
     */
    void collectHeartbeatData(HeartbeatResponse response);

}
