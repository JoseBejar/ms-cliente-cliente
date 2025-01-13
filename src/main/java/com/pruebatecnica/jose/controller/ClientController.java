package com.pruebatecnica.jose.controller;


import com.pruebatecnica.jose.api.ClientControllerApi;
import com.pruebatecnica.jose.dto.ClientDto;
import com.pruebatecnica.jose.dto.MetricasEdadDto;
import com.pruebatecnica.jose.dto.MetricasEdadDtoEdit;
import com.pruebatecnica.jose.service.ClientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
@RestController
public class ClientController implements ClientControllerApi {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDto> clientById(Long idClient) {
        System.out.println("INICIALIZANDO PROCESO");
        Optional<ClientDto> clientDto = clientService.clientById(idClient);
        if (clientDto.isPresent()) {
            System.out.println("TERMINANDO PROCESO");
            return ResponseEntity.ok(clientDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> createClient(ClientDto clientDto) {
        System.out.println("INICIALIZANDO PROCESO");
        try {
            clientService.createClient(clientDto);
            System.out.println("TERMINANDO PROCESO");
            return ResponseEntity.status(201).body("Cliente creado correctamente");
        }catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<ClientDto>> listClient() {
        System.out.println("INICIALIZANDO PROCESO");
        List<ClientDto> clients = clientService.listClient();
        System.out.println("TERMINANDO PROCESO" + clients);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(clients);
    }

    @Override
    public ResponseEntity<String> updateClient(Long idClient, ClientDto clientDto) {
        System.out.println("INICIALIZANDO PROCESO");
        try {
            String dataService = clientService.updateClient(clientDto, idClient);
            System.out.println("TERMINANDO PROCESO");
            return ResponseEntity.ok(dataService);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<MetricasEdadDto> obtenerMetricasEdad() {
        return ResponseEntity.ok(clientService.obtenerMetricasEdad());
    }

}


