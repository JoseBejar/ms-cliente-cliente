package com.pruebatecnica.jose.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_client")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @JsonProperty("name")
    private String name;
    @JsonProperty("appMaterno")
    private String appMaterno;
    @JsonProperty("appPaterno")
    private String appPaterno;
    @JsonProperty("birthdate")
    private LocalDate birthdate;
    @JsonProperty("edad")
    private String edad;


    @Id
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppMaterno() {
        return appMaterno;
    }

    public void setAppMaterno(String appMaterno) {
        this.appMaterno = appMaterno;
    }

    public String getAppPaterno() {
        return appPaterno;
    }

    public void setAppPaterno(String appPaterno) {
        this.appPaterno = appPaterno;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
