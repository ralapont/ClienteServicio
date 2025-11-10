package com.rafael.cliente.mapper;

import com.rafael.cliente.dtos.ClienteRequest;
import com.rafael.cliente.model.entity.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClienteRequestMapper {

    Cliente toEntity(ClienteRequest dto);

    ClienteRequest toDto(Cliente entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClienteRequest dto, @MappingTarget Cliente entity);
}
