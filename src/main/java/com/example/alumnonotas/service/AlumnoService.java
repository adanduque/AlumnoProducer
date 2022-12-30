package com.example.alumnonotas.service;

import com.example.alumnonotas.model.AlumnoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import repo.AlumnoRepository;

@Service
public class AlumnoService {

    private final AlumnoEventService alumnoEventService;



    @Autowired
    private MongoTemplate mongoTemplate;

    public AlumnoService(AlumnoEventService alumnoEventService) {
        super();
        this.alumnoEventService = alumnoEventService;
    }

    public void save(AlumnoModel alumno){
        System.out.println("Ingreso : save!!");
        this.alumnoEventService.publish(alumno);

    }

    public AlumnoModel findAlumno(String dni){
        System.out.println("Ingreso : find!!");
        Query query = new Query();
        query.addCriteria(Criteria.where("dni").is(dni));
        AlumnoModel alumno= mongoTemplate.findOne(query,AlumnoModel.class);
        return  alumno;
    }
}
