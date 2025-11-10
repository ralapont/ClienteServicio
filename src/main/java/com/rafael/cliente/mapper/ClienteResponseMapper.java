package com.rafael.cliente.mapper;

import com.rafael.cliente.dtos.ClienteResponse;
import com.rafael.cliente.model.entity.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClienteResponseMapper {

    Cliente toEntity(ClienteResponse dto);

    ClienteResponse toDto(Cliente entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClienteResponse dto, @MappingTarget Cliente entity);
}
