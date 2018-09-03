package com.styoanovst.task.farmingmanagementsystem.repository;

import com.styoanovst.task.farmingmanagementsystem.model.Tractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TractorRepository extends CrudRepository<Tractor, Long> {

    @Override
    List<Tractor> findAll();
    Tractor findByName(String name);
}
