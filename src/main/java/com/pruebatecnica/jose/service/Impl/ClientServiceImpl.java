package com.pruebatecnica.jose.service.Impl;

import com.pruebatecnica.jose.dto.ClientDto;

import com.pruebatecnica.jose.dto.MetricasEdadDto;
import com.pruebatecnica.jose.dto.MetricasEdadDtoEdit;
import com.pruebatecnica.jose.exception.ClientAlreadyExistsException;
import com.pruebatecnica.jose.exception.ClientNotFoundException;
import com.pruebatecnica.jose.model.Client;
import com.pruebatecnica.jose.repository.ClientRepository;
import com.pruebatecnica.jose.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> listClient() {
        List<Client> clients = clientRepository.findAll();  // Usamos findAll() para obtener los clientes desde la base de datos

        // Convertimos los clientes a ClientDto y calculamos la esperanza de vida para cada uno
        return clients.stream()
                .map(client -> {
                    ClientDto clientDto = new ClientDto();
                    clientDto.setId(client.getId());
                    clientDto.setName(client.getName());
                    clientDto.setAppMaterno(client.getAppMaterno());
                    clientDto.setAppPaterno(client.getAppPaterno());
                    clientDto.setBirthdate(client.getBirthdate());
                    clientDto.setEdad(String.valueOf(ChronoUnit.YEARS.between(client.getBirthdate(), LocalDate.now())));

                    // Calcular la esperanza de vida y asignarla al cliente
                    LocalDate esperanzaVida = calcularEsperanzaVida(clientDto);
                    clientDto.setFechaestimadaevento(esperanzaVida);

                    return clientDto;
                })
                .collect(Collectors.toList());
    }

    public LocalDate calcularEsperanzaVida(ClientDto clientDto) {
        int esperanzaDeVidaPromedio = 80; // Promedio de esperanza de vida en años

        // Calculamos los años restantes basados en la edad
        long edad = ChronoUnit.YEARS.between(clientDto.getBirthdate(), LocalDate.now());
        long añosRestantes = esperanzaDeVidaPromedio - edad;

        // Calculamos la fecha estimada para la esperanza de vida
        return clientDto.getBirthdate().plusYears(añosRestantes);
    }


    @Override
    public String createClient(ClientDto clientDto) {
        List<Client> clientsByName = clientRepository.findByName(clientDto.getName());
        if (!clientsByName.isEmpty()) {
            throw new ClientAlreadyExistsException("Ya existe un cliente con el nombre: " + clientDto.getName());
        }

        Client client = toEntity(clientDto); // Uso del método toEntity
        clientRepository.save(client);
        return "Creado correctamente";
    }

    @Override
    public Optional<ClientDto> clientById(Long idClient) {
        Optional<Client> clientOptional = clientRepository.findById(idClient);
        return clientOptional.map(this::toDto); // Uso del método toDto
    }

    @Override
    public String updateClient(ClientDto clientDto, Long idClient) {
        Optional<Client> dataClientOptional = clientRepository.findById(idClient);

        if (dataClientOptional.isEmpty()) {
            throw new ClientNotFoundException("Cliente no encontrado con ID: " + idClient);
        }

        Client existingClient = dataClientOptional.get();

        // Validar si ya existe un cliente con el mismo nombre, excluyendo el actual
        List<Client> clientsByName = clientRepository.findByName(clientDto.getName());
        if (!clientsByName.isEmpty() && !clientsByName.get(0).getId().equals(existingClient.getId())) {
            throw new ClientAlreadyExistsException("Ya existe un cliente con el nombre: " + clientDto.getName());
        }

        clientDto.setId(existingClient.getId());
        // Actualizar la entidad existente con los datos del DTO
        existingClient = toEntity(clientDto); // Uso del método toEntity

        // Guardar el cliente actualizado
        Client updatedClient = clientRepository.save(existingClient);

        return "Actualizado correctamente el ID: " + updatedClient.getId();
    }

    // Método para convertir de Client a ClientDto
    public ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        }

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setAppPaterno(client.getAppPaterno());
        clientDto.setAppMaterno(client.getAppMaterno());
        clientDto.setBirthdate(client.getBirthdate());
        clientDto.setEdad(client.getEdad());

        return clientDto;
    }

    public MetricasEdadDto obtenerMetricasEdad() {
        List<Object[]> result = clientRepository.obtenerMetricasEdad();
        MetricasEdadDto oMetricasEdadDto = new MetricasEdadDto();
        if (!result.isEmpty()) {
            Double promedioEdad = (Double) result.get(0)[0]; // El primer valor es el promedio
            Double desviacionEstandarEdad = (Double) result.get(0)[1]; // El segundo valor es la desviación estándar

            oMetricasEdadDto.setPromedioEdad(promedioEdad);
            oMetricasEdadDto.setDesviacionEstandarEdad(desviacionEstandarEdad);
        }

        return oMetricasEdadDto;
    }

    // Método para convertir de ClientDto a Client
    public Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setAppPaterno(clientDto.getAppPaterno());
        client.setAppMaterno(clientDto.getAppMaterno());
        //client.setBirthdate(clientDto.getBirthdate());
        client.setEdad(clientDto.getEdad());

        return client;
    }
}
