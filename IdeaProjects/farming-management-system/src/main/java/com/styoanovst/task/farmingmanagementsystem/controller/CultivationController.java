package com.styoanovst.task.farmingmanagementsystem.controller;

import com.styoanovst.task.farmingmanagementsystem.dto.CultivationDto;
import com.styoanovst.task.farmingmanagementsystem.dto.PlotDto;
import com.styoanovst.task.farmingmanagementsystem.dto.TractorDto;
import com.styoanovst.task.farmingmanagementsystem.service.CultivationService;
import com.styoanovst.task.farmingmanagementsystem.service.PlotService;
import com.styoanovst.task.farmingmanagementsystem.service.TractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CultivationController {

    @Autowired
    private CultivationService cultivationService;

    @Autowired
    private PlotService plotService;

    @Autowired
    private TractorService tractorService;

    @RequestMapping(method = RequestMethod.POST, path = "/cultivation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCultivation(HttpServletResponse response, CultivationDto cultivationDto) throws IOException {
        try {
            PlotDto plotDto = plotService.getPlotById(cultivationDto.getPlotId());
            if (cultivationDto.getFarmedArea() > plotDto.getArea()) response.sendRedirect("/add-cultivation?error");
            cultivationService.createCultivation(cultivationDto);
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/add-cultivation?error");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add-cultivation")
    public ModelAndView addCultivation() {
        ModelAndView modelAndView = new ModelAndView("/add-cultivation");
        List<PlotDto> plotDtos = plotService.getAllPlots();
        modelAndView.addObject("plotDtos", plotDtos);
        List<TractorDto> tractorDtos = tractorService.getAllTractors();
        modelAndView.addObject("tractorDtos", tractorDtos);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/report-cultivation")
    public ModelAndView getAllCultivations()
    {
        ModelAndView modelAndView = new ModelAndView("report-cultivation");
        List<CultivationDto> cultivationdDtos = cultivationService.getAllCultivations();
        modelAndView.addObject("cultivationDtos", cultivationdDtos);
        List<PlotDto> plotDtos = cultivationdDtos.stream()
                .map(s -> plotService.getPlotById(s.getPlotId()))
                .collect(Collectors.toList());
        List<TractorDto> tractorDtos = cultivationdDtos.stream()
                .map(s -> tractorService.getTractorById(s.getTractorId()))
                .collect(Collectors.toList());

        modelAndView.addObject("plotDtos", plotDtos);
        modelAndView.addObject("tractorDtos", tractorDtos);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/cultivation/{id}")
    public CultivationDto getCultivationById(@PathVariable Long id) {
        return cultivationService.getCultivationById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/cultivation/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCultivation(@PathVariable Long id, CultivationDto cultivationDto) {
        cultivationService.updateCultivations(id, cultivationDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/cultivation/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCultivation(@PathVariable Long id) {
        cultivationService.deleteCultivation(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/cultivation?filter={filter}")
    public ModelAndView filterBy(@PathVariable String filter) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("report-cultivation");
        List<CultivationDto> cultivationdDtos = cultivationService.filterBy(filter);
        modelAndView.addObject("cultivationDtos", cultivationdDtos);
        List<PlotDto> plotDtos = cultivationdDtos.stream()
                .map(s -> plotService.getPlotById(s.getPlotId()))
                .collect(Collectors.toList());
        List<TractorDto> tractorDtos = cultivationdDtos.stream()
                .map(s -> tractorService.getTractorById(s.getTractorId()))
                .collect(Collectors.toList());

        modelAndView.addObject("plotDtos", plotDtos);
        modelAndView.addObject("tractorDtos", tractorDtos);
        return modelAndView;
    }
}
