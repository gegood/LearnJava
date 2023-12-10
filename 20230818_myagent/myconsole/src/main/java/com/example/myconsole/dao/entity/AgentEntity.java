package com.example.myconsole.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AgentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
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
