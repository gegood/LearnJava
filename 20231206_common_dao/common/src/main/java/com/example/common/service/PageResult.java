package com.example.common.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Schema(description = "分页结果")
@Data
@Setter
@AllArgsConstructor
public class PageResult<T> {

    @Schema(description = "总数", minimum = "0")
    private final long total;

    @Schema(description = "页码", minimum = "0")
    private final int page;

    @Schema(description = "分页大小", minimum = "0", defaultValue = "10")
    private final int size;

    @Schema(description = "内容")
    private final List<T> content;

}
