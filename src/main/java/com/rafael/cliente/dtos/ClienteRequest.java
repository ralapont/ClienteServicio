package com.rafael.cliente.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @JsonProperty("nombre")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "Los apellidos no pueden tener más de 100 caracteres")
    @JsonProperty("apellidos")
    private String apellidos;

    @NotBlank(message = "El NIF es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "El NIF debe tener 8 dígitos seguidos de una letra mayúscula")
    @JsonProperty("nif")
    private String nif;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[6|7|9][0-9]{8}$", message = "El teléfono debe tener 9 dígitos y empezar por 6, 7 o 9")
    @JsonProperty("telefono")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @JsonProperty("email")
    private String email;

}
