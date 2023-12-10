package com.example.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class LogicalDeletePo<K> implements Po<K> {

    @Column(name = "deleted")
    private Integer deleted = 0;

}
