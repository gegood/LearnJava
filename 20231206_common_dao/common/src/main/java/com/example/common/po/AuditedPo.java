package com.example.common.po;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AuditedPo<K> extends LogicalDeletePo<K> {

    @CreatedBy
    @Column(name = "creator")
    private String creator;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private Long createdDate;

    @LastModifiedBy
    @Column(name = "modifier")
    private String modifier;

    @LastModifiedDate
    @Column(name = "modified_date")
    private Long modifiedDate;

}
