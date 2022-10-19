package io.ugurh.ecommerce.mapper;

import org.mapstruct.Mapper;

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

}
