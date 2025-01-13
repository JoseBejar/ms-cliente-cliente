package com.pruebatecnica.jose;

import com.pruebatecnica.jose.model.Client;
import com.pruebatecnica.jose.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientTests {
    @Autowired
    private ClientRepository clientRepository;
/*
    @Test
    public void testSaveClient(){
        Client notes = new Client();
        notes.setNote("10");
        notes.setIduser(Long.valueOf(1));
        Client savedClient = clientRepository.save(notes);
    }*/
}