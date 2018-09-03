package com.styoanovst.task.farmingmanagementsystem.controller;

import com.styoanovst.task.farmingmanagementsystem.dto.TractorDto;
import com.styoanovst.task.farmingmanagementsystem.service.TractorService;
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
public class TractorController {

    @Autowired
    private TractorService tractorService;

    @RequestMapping(method = RequestMethod.POST, path = "/tractor")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTractor(HttpServletResponse response, TractorDto tractorDto) throws IOException {

        try {
            tractorService.createTractor(tractorDto);
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/add-tractor?error");
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add-tractor")
    public String addTractor(){
        return "add-tractor";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tractor")
    public List<TractorDto> getTractors() {
        return tractorService.getAllTractors();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/tractor/{id}")
    public TractorDto getTractorById(@PathVariable Long id) {
        return tractorService.getTractorById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/tractor/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editTractor(@PathVariable Long id, TractorDto tractorDto) {
        tractorService.editTractor(id, tractorDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/tractor/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTractor(@PathVariable Long id) {
        tractorService.deleteTractor(id);
    }
}
