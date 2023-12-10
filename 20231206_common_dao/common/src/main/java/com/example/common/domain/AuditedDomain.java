package com.example.common.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AuditedDomain<K> implements Domain<K> {

    @Schema(description = "创建者，由服务端自动记录，无需提交", accessMode = AccessMode.READ_ONLY, nullable = false)
    private String creator;

    @Schema(description = "创建时间，毫秒时间戳，由服务端自动记录，无需提交", accessMode = AccessMode.READ_ONLY, nullable = false)
    private Long createdDate;

    @Schema(description = "修改者，由服务端自动记录，无需提交", accessMode = AccessMode.READ_ONLY, nullable = true)
    private String modifier;

    @Schema(description = "修改时间，毫秒时间戳，由服务端自动记录，无需提交", accessMode = AccessMode.READ_ONLY, nullable = true)
    private Long modifiedDate;

}
