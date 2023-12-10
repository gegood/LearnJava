package com.example.myconsole.heartbeat.domain;

import com.example.common.domain.AuditedDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@Schema(description = "agent表")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class AgentDo extends AuditedDomain<Long> {

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
