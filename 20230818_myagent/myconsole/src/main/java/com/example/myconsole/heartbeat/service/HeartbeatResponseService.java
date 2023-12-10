package com.example.myconsole.heartbeat.service;

import com.example.common.heartbeat.HeartbeatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Slf4j
@Service
public class HeartbeatResponseService {

    @Autowired
    private List<HeartbeatResponseDataSource> heartbeatDataSourceList;

    public HeartbeatResponse collectHeartbeatResponse() {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setRespId(System.currentTimeMillis());
        invokeDataSource(ds -> ds.collectHeartbeatData(response));
        return response;
    }

    /**
     * 遍历所有的 HeartbeatDataSource 的实现类，将数据插入到 response
     * @param consumer
     */
    private void invokeDataSource(Consumer<HeartbeatResponseDataSource> consumer) {
        if (CollectionUtils.isEmpty(heartbeatDataSourceList)) {
            return;
        }
        heartbeatDataSourceList.forEach(dataSource -> {
            try {
                consumer.accept(dataSource);
            } catch (Throwable t) {
                log.error("[Heartbeat] invoke data source error", t);
            }
        });
    }
}
