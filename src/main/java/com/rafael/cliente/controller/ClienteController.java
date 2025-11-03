package com.rafael.cliente.controller;

import com.rafael.cliente.dtos.ClienteDTO;
import com.rafael.cliente.exceptionbussines.ClienteNotFoundException;
import com.rafael.cliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto) {
        ClienteDTO created = clienteService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        ClienteDTO dto = clienteService.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> list(Pageable pageable) {
        Page<ClienteDTO> page = clienteService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        ClienteDTO updated = clienteService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDTO> partialUpdate(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        ClienteDTO updated = clienteService.partialUpdate(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ClienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
