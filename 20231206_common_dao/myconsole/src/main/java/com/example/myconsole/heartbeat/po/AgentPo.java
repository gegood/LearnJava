package com.example.myconsole.heartbeat.po;

import com.example.common.po.AuditedPo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "agent")
@SQLDelete(sql = "UPDATE agent SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
public class AgentPo extends AuditedPo<Long> {

    /**  主键  */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "agent_id", nullable = false, unique = true)
    private Long agentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "port", nullable = false)
    private Integer port;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "report_time", nullable = false)
    private Long reportTime;

}
