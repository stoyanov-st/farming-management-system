package com.styoanovst.task.farmingmanagementsystem.controller;

import com.styoanovst.task.farmingmanagementsystem.dto.PlotDto;
import com.styoanovst.task.farmingmanagementsystem.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class PlotController {

    @Autowired
    private PlotService plotService;

    @RequestMapping(method = RequestMethod.POST, path = "/plot")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlot(HttpServletResponse response, PlotDto plotDto) throws IOException {

        try {
            plotService.createPlot(plotDto);
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/add-plot?error");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "add-plot")
    public String addPlot() {
        return "add-plot";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/plot")
    public List<PlotDto> getPlots() {
        return plotService.getAllPlots();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/plot/{id}")
    public PlotDto getPlotById(@PathVariable Long id) {
        return plotService.getPlotById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/plot/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editTractor(@PathVariable Long id, PlotDto plotDto) {
        plotService.editPlot(id, plotDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/plot/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePlot(@PathVariable Long id) {
        plotService.deletePlot(id);
    }
}
