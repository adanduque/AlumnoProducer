package com.example.alumnonotas.api;

import com.example.alumnonotas.service.AlumnoService;
import com.example.alumnonotas.model.AlumnoModel;
import lombok.Getter;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody AlumnoModel alumnoModel){
        alumnoService.save(alumnoModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping({"/{dni}"})
    public AlumnoModel mejoresHuellas(@PathVariable String dni)
    {
        return  alumnoService.findAlumno(dni);
    }
    
    @GetMapping()
    public ResponseEntity verificarConexion()
    {
      return new ResponseEntity<>(HttpStatus.OK);
    }
}
