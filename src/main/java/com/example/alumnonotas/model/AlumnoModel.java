package com.example.alumnonotas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
@Data
public class AlumnoModel {

    private String dni;
    private String nombre;
    private List<Integer> notas;

    private String promedio ;
}
