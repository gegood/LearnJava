package com.example.myconsole.heartbeat.service;

import com.example.common.service.SearchCondition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "agent表")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentSearchCondition implements SearchCondition {

    /**  主键  */
    @Schema(name = "id")
    private Long id;

    @Schema(name = "agent_id")
    private Long agentId;

    @Schema(name = "name")
    private String name;

    @Schema(name = "port")
    private Integer port;

    @Schema(name = "status")
    private Integer status;

    @Schema(name = "report_time")
    private Long reportTime;
}
