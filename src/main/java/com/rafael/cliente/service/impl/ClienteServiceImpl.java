package com.rafael.cliente.service.impl;

import com.rafael.cliente.dtos.ClienteDTO;
import com.rafael.cliente.exceptionbussines.ClienteNotFoundException;
import com.rafael.cliente.mapper.ClienteMapper;
import com.rafael.cliente.model.entity.Cliente;
import com.rafael.cliente.model.repository.ClienteRepository;
import com.rafael.cliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteDTO create(ClienteDTO dto) {
        log.info("Creating new Cliente: {}", dto);
        Cliente entity = clienteMapper.toEntity(dto);
        Cliente saved = clienteRepository.save(entity);
        return clienteMapper.toDto(saved);
    }

    @Override
    public Optional<ClienteDTO> findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return Optional.of(clienteMapper.toDto(cliente));
    }

    @Override
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ClienteDTO> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::toDto);
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO dto) {
        return clienteRepository.findById(id)
                .map(existing -> {
                    clienteMapper.updateEntityFromDto(dto, existing);
                    Cliente saved = clienteRepository.save(existing);
                    return clienteMapper.toDto(saved);
                })
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public ClienteDTO partialUpdate(Long id, ClienteDTO dto) {
        return clienteRepository.findById(id)
                .map(existing -> {
                    clienteMapper.updateEntityFromDto(dto, existing);
                    Cliente saved = clienteRepository.save(existing);
                    return clienteMapper.toDto(saved);
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
}
