package com.styoanovst.task.farmingmanagementsystem.dto;

import com.styoanovst.task.farmingmanagementsystem.model.Plot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class PlotDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String crops;

    @NotNull
    private Float area;

    public PlotDto() {};

    public PlotDto(Plot plot) {
        this.id = plot.getId();
        this.name = plot.getName();
        this.crops = plot.getCrops();
        this.area = plot.getArea();
    }
}
