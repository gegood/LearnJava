package com.example.common.heartbeat;

import com.example.common.heartbeat.data.Console;
import lombok.Data;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Data
public class HeartbeatResponse {

    /**
     * 返回的数据
     */
    private Long respId;

    private Console console;

}
