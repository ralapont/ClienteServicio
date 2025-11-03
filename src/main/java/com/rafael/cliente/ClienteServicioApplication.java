package com.rafael.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClienteServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServicioApplication.class, args);
	}

}
