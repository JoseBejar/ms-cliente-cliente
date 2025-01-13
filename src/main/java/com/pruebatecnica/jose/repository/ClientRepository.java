package com.pruebatecnica.jose.repository;

import com.pruebatecnica.jose.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByName(String name);

    @Query("SELECT AVG(c.edad) AS promedioEdad, STDDEV(c.edad) AS desviacionEstandarEdad FROM Client c")
    List<Object[]> obtenerMetricasEdad();
}