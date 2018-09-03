package com.styoanovst.task.farmingmanagementsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Cultivation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "plot_id")
    private Plot plot;

    @OneToOne
    @JoinColumn(name = "tractor_id")
    private Tractor tractor;

    private Date farmingDate;
    private Float farmedArea;
}
