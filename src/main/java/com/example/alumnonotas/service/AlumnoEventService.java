package com.example.alumnonotas.service;

import com.example.alumnonotas.event.AlumnoCreatedEvent;
import com.example.alumnonotas.event.Event;
import com.example.alumnonotas.event.EventType;
import com.example.alumnonotas.model.AlumnoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class AlumnoEventService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("alumno_notas")
    private String topicAlumnos;

    public void publish(AlumnoModel alumno) {
        AlumnoCreatedEvent created = new AlumnoCreatedEvent();
        created.setData(alumno);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());
        this.producer.send(topicAlumnos,created);

           }

}
