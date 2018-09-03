package com.styoanovst.task.farmingmanagementsystem.dto;

import com.styoanovst.task.farmingmanagementsystem.model.Cultivation;
import com.styoanovst.task.farmingmanagementsystem.service.PlotService;
import com.styoanovst.task.farmingmanagementsystem.service.TractorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CultivationDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Date farmingDate;

    @NotNull
    private Float farmedArea;

    @NotNull
    private Long plotId;

    @NotNull
    private Long tractorId;

    public CultivationDto() {}

    public CultivationDto(Cultivation cultivation) {
        this.id = cultivation.getId();
        this.farmingDate = cultivation.getFarmingDate();
        this.farmedArea = cultivation.getFarmedArea();
        this.tractorId = cultivation.getTractor().getId();
        this.plotId = cultivation.getPlot().getId();
    }
}
