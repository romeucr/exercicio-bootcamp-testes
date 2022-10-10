package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO eventDTO) {
        Event eventDB = repository.getOne(id);
        eventDB.setDate(eventDTO.getDate());
        eventDB.setName(eventDTO.getName());
        eventDB.setUrl(eventDTO.getUrl());
        eventDB.setCity(cityRepository.findById(eventDTO.getCityId()).orElseThrow(EntityNotFoundException::new));
        return new EventDTO(repository.save(eventDB));
    }

}