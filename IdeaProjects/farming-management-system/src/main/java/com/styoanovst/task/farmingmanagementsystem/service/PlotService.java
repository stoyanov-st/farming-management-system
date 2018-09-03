package com.styoanovst.task.farmingmanagementsystem.service;

import com.styoanovst.task.farmingmanagementsystem.dto.PlotDto;
import com.styoanovst.task.farmingmanagementsystem.model.Plot;
import com.styoanovst.task.farmingmanagementsystem.repository.PlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlotService {

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void createPlot(PlotDto plotDto) throws Exception {
        if (plotRepository.findByName(plotDto.getName()) == null) {
            Plot plot = dtoToEntity(plotDto);
            plotRepository.save(plot);
        } else throw new Exception("Existing plot!");
    }


    public List<PlotDto> getAllPlots() {
        return plotRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors
                        .toList());
    }

    public PlotDto getPlotById(Long id) {
        return modelMapper.map(plotRepository.findById(id), PlotDto.class);
    }

    public void editPlot(Long id, PlotDto plotDto) {
        Plot plot = dtoToEntity(plotDto);
        plot.setId(id);
        plotRepository.save(plot);
    }

    public void deletePlot(Long id) {
        plotRepository.deleteById(id);
    }

    private Plot dtoToEntity(PlotDto plotDto) {
        return modelMapper.map(plotDto, Plot.class);
    }

    private PlotDto entityToDto(Plot plot) {
        return modelMapper.map(plot, PlotDto.class);
    }
}
