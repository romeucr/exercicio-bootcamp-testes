package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;
    @Transactional
    public List<CityDTO> findAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(City::getName))
                .map(CityDTO::new).collect(Collectors.toList());
    }

    public CityDTO insert(CityDTO cityDTO) {
        City entity = new City(cityDTO.getId(), cityDTO.getName());
        return new CityDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
