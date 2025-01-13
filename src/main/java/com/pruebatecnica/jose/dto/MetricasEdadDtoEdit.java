package com.pruebatecnica.jose.dto;

public class MetricasEdadDtoEdit {

    private Double promedioEdad;
    private Double desviacionEstandarEdad;

    public MetricasEdadDtoEdit(Double promedioEdad, Double desviacionEstandarEdad) {
        this.promedioEdad = promedioEdad;
        this.desviacionEstandarEdad = desviacionEstandarEdad;
    }

    public Double getPromedioEdad() {
        return promedioEdad;
    }

    public void setPromedioEdad(Double promedioEdad) {
        this.promedioEdad = promedioEdad;
    }

    public Double getDesviacionEstandarEdad() {
        return desviacionEstandarEdad;
    }

    public void setDesviacionEstandarEdad(Double desviacionEstandarEdad) {
        this.desviacionEstandarEdad = desviacionEstandarEdad;
    }
}