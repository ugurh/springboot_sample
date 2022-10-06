package io.hrnugr.sample.mapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BaseMapper {
    Object toDto(Object obj);

    Object toEntity(Object obj);

    List<Object> toListDto(List<Object> list);
}
