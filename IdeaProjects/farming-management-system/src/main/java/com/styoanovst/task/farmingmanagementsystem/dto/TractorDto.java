package com.styoanovst.task.farmingmanagementsystem.dto;

import com.styoanovst.task.farmingmanagementsystem.model.Tractor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class TractorDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    public TractorDto() {};

    public TractorDto(Tractor tractor) {
        this.id = tractor.getId();
        this.name = tractor.getName();
    }
}
