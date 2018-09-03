package com.styoanovst.task.farmingmanagementsystem.repository;

import com.styoanovst.task.farmingmanagementsystem.model.Plot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlotRepository extends CrudRepository<Plot, Long> {

    @Override
    List<Plot> findAll();
    Plot findByName(String name);
}
