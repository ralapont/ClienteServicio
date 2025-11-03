package com.rafael.cliente.service;

import com.rafael.cliente.dtos.ClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
        ClienteDTO create(ClienteDTO dto);
        Optional<ClienteDTO> findById(Long id);
        List<ClienteDTO> findAll();
        Page<ClienteDTO> findAll(Pageable pageable);
        ClienteDTO update(Long id, ClienteDTO dto);
        ClienteDTO partialUpdate(Long id, ClienteDTO dto);
        void deleteById(Long id);
        boolean existsById(Long id);
}
