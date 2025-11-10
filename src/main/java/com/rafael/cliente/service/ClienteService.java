package com.rafael.cliente.service;

import com.rafael.cliente.dtos.ClienteRequest;
import com.rafael.cliente.dtos.ClienteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
        ClienteResponse create(ClienteRequest dto, String username);
        Optional<ClienteResponse> findById(Long id);
        List<ClienteResponse> findAll();
        Page<ClienteResponse> findAll(Pageable pageable);
        ClienteResponse update(Long id, ClienteRequest dto);
        ClienteResponse partialUpdate(Long id, ClienteRequest dto);
        void deleteById(Long id);
        boolean existsById(Long id);
        boolean userExists(String username);
}
