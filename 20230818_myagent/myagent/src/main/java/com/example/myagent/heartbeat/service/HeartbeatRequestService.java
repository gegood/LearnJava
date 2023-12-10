package com.example.myagent.heartbeat.service;

import com.example.common.heartbeat.HeartbeatRequest;
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
public class HeartbeatRequestService {

    @Autowired
    private List<HeartbeatRequestDataSource> heartbeatDataSourceList;

    public HeartbeatRequest collectHeartbeatRequest() {
        HeartbeatRequest request = new HeartbeatRequest();
        request.setReqId(System.currentTimeMillis());
        invokeDataSource(ds -> ds.collectHeartbeatData(request));
        return request;
    }

    /**
     * 遍历所有的 HeartbeatDataSource 的实现类，将数据插入到 request
     * @param consumer
     */
    private void invokeDataSource(Consumer<HeartbeatRequestDataSource> consumer) {
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
