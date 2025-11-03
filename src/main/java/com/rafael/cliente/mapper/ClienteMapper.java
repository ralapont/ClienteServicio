package com.rafael.cliente.mapper;

import com.rafael.cliente.dtos.ClienteDTO;
import com.rafael.cliente.model.entity.Cliente;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDto(Cliente entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClienteDTO dto, @MappingTarget Cliente entity);
}
