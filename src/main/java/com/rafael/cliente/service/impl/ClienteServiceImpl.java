package com.rafael.cliente.service.impl;

import com.rafael.cliente.dtos.ClienteRequest;
import com.rafael.cliente.dtos.ClienteResponse;
import com.rafael.cliente.exceptionbussines.ClienteNotFoundException;
import com.rafael.cliente.feign.AuthServiceClient;
import com.rafael.cliente.mapper.ClienteRequestMapper;
import com.rafael.cliente.mapper.ClienteResponseMapper;
import com.rafael.cliente.model.entity.Cliente;
import com.rafael.cliente.model.repository.ClienteRepository;
import com.rafael.cliente.service.ClienteService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteRequestMapper clienteRequestMapper;
    private final ClienteResponseMapper clienteResponseMapper;
    private final AuthServiceClient authServiceClient;

    @Override
    public ClienteResponse create(ClienteRequest dto, String username) {
        log.info("Creating new Cliente: {}", dto);
        Cliente entity = clienteRequestMapper.toEntity(dto);
        entity.setUsername(username);
        Cliente saved = clienteRepository.save(entity);
        return clienteResponseMapper.toDto(saved);
    }

    @Override
    public Optional<ClienteResponse> findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return Optional.of(clienteResponseMapper.toDto(cliente));
    }

    @Override
    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClienteResponse> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteResponseMapper::toDto);
    }

    @Override
    public ClienteResponse update(Long id, ClienteRequest dto) {
        return clienteRepository.findById(id)
                .map(existing -> {
                    clienteRequestMapper.updateEntityFromDto(dto, existing);
                    Cliente saved = clienteRepository.save(existing);
                    return clienteResponseMapper.toDto(saved);
                })
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public ClienteResponse partialUpdate(Long id, ClienteRequest dto) {
        return clienteRepository.findById(id)
                .map(existing -> {
                    clienteRequestMapper.updateEntityFromDto(dto, existing);
                    Cliente saved = clienteRepository.save(existing);
                    return clienteResponseMapper.toDto(saved);
                })
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public boolean userExists(String username) {
        try {
            ResponseEntity<Void> response = authServiceClient.checkUserExists(username);
            return response.getStatusCode().is2xxSuccessful();
        } catch (FeignException.NotFound e) {
            return false;
        } catch (FeignException e) {
            // manejar otros errores si es necesario
            log.info("Error al verificar usuario: {}", e.getMessage());
            throw new RuntimeException("Error al verificar usuario", e);
        }
    }

}
