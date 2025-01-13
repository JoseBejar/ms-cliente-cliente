package com.pruebatecnica.jose.service;

import com.pruebatecnica.jose.dto.ClientDto;
import com.pruebatecnica.jose.dto.MetricasEdadDto;
import com.pruebatecnica.jose.dto.MetricasEdadDtoEdit;

import java.util.List;
import java.util.Optional;

public interface ClientService {
 List<ClientDto> listClient();
 String createClient(ClientDto client);
 Optional<ClientDto> clientById(Long client);
 String updateClient(ClientDto client, Long idClient);
 MetricasEdadDto obtenerMetricasEdad();
}
