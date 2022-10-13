package io.hrnugr.ecommerce.mapper;

import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author harun ugur
 */
@Mapper
public interface BaseMapper {
    /**
     * mapping entity to data transfer object
     *
     * @param obj entity
     * @return Object
     */
    Object toDto(Object obj);

    /**
     * mapping data transfer object to entity
     *
     * @param obj data transfer object
     * @return Object
     */
    Object toEntity(Object obj);

    /**
     * mapping entities to data transfer objects
     *
     * @param list entities
     * @return Object
     */
    List<Object> toListDto(List<Object> list);
}
