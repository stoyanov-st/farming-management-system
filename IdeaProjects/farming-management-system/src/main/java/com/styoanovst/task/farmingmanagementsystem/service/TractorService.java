package com.styoanovst.task.farmingmanagementsystem.service;

import com.styoanovst.task.farmingmanagementsystem.dto.TractorDto;
import com.styoanovst.task.farmingmanagementsystem.model.Tractor;
import com.styoanovst.task.farmingmanagementsystem.repository.TractorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TractorService {

    @Autowired
    private TractorRepository tractorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void createTractor(TractorDto tractorDto) throws Exception {
        if (tractorRepository.findByName(tractorDto.getName()) == null) {
            Tractor tractor = dtoToEntity(tractorDto);
            tractorRepository.save(tractor);
        } else throw new Exception("Existing tractor!");
    }


    public List<TractorDto> getAllTractors() {
        return tractorRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public TractorDto getTractorById(Long id) {
        return modelMapper.map(tractorRepository.findById(id), TractorDto.class);
    }

    public void editTractor(Long id, TractorDto tractorDto) {
        Tractor tractor = dtoToEntity(tractorDto);
        tractor.setId(id);
        tractorRepository.save(tractor);
    }

    public void deleteTractor(Long id) {
        tractorRepository.deleteById(id);
    }

    private Tractor dtoToEntity(TractorDto tractorDto) {
        return modelMapper.map(tractorDto, Tractor.class);
    }

    private TractorDto entityToDto(Tractor tractor) {
        return modelMapper.map(tractor, TractorDto.class);
    }


}
