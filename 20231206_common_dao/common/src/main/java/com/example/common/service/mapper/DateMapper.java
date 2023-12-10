package com.example.common.service.mapper;

import org.mapstruct.Mapper;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface DateMapper {

    default Long toLong(Date date) {
        return date == null ? null : date.getTime();
    }

    default Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

}
