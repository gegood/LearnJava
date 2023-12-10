package com.example.myconsole.heartbeat.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "agent表分页请求参数")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonPropertyOrder({"id", "agent_id", "name", "port", "status", "reportTime"})
public class AgentPageReturn {

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
