package com.styoanovst.task.farmingmanagementsystem.service;

import com.styoanovst.task.farmingmanagementsystem.dto.CultivationDto;
import com.styoanovst.task.farmingmanagementsystem.model.Cultivation;
import com.styoanovst.task.farmingmanagementsystem.model.Plot;
import com.styoanovst.task.farmingmanagementsystem.model.Tractor;
import com.styoanovst.task.farmingmanagementsystem.repository.CultivationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("/cultivation")
public class CultivationService {

    @Autowired
    private CultivationRepository cultivationRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void createCultivation(CultivationDto cultivationDto) throws Exception {
        if (cultivationRepository.findById(cultivationDto.getId()).isPresent()) {
            Cultivation cultivation = dtoToEntity(cultivationDto);
            cultivationRepository.save(cultivation);
        } else throw new Exception("Existing Cultivation!");
    }

    public List<CultivationDto> getAllCultivations() {
        return cultivationRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors
                        .toList());
    }

    public CultivationDto getCultivationById(Long id) {
        return  modelMapper.map(cultivationRepository.findById(id), CultivationDto.class);
    }

    public void deleteCultivation(Long id) {
        cultivationRepository.deleteById(id);
    }

    public void updateCultivations(Long id, CultivationDto cultivationDto) {
        Cultivation cultivation = dtoToEntity(cultivationDto);
        cultivation.setId(id);

        cultivationRepository.save(cultivation);
    }

    public List<CultivationDto> filterBy(String filter) throws ParseException {
        return Stream.of(cultivationRepository.findByPlotNameOrPlotCropsOrFarmingDateOrTractorName(filter, filter, new SimpleDateFormat().parse(filter), filter))
                .map(s -> entityToDto(s.get()))
                .collect(Collectors.toList());
    }

    private Cultivation dtoToEntity(CultivationDto cultivationDto) {
        Cultivation cultivation = modelMapper.map(cultivationDto, Cultivation.class);
        PlotService plotService = new PlotService();
        Plot plot = modelMapper.map(plotService.getPlotById(cultivationDto.getPlotId()), Plot.class);
        cultivation.setPlot(plot);
        TractorService tractorService = new TractorService();
        Tractor tractor = modelMapper.map(tractorService.getTractorById(cultivationDto.getTractorId()), Tractor.class);
        cultivation.setTractor(tractor);
        return cultivation;
    }

    private CultivationDto entityToDto(Cultivation cultivation) {

        CultivationDto cultivationDto = modelMapper.map(cultivation, CultivationDto.class);
        cultivationDto.setPlotId(cultivation.getPlot().getId());
        cultivationDto.setTractorId(cultivation.getTractor().getId());

        return cultivationDto;


    }
}
