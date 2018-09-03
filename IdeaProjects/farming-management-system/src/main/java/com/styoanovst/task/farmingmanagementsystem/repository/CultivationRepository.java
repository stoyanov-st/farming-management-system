package com.styoanovst.task.farmingmanagementsystem.repository;

import com.styoanovst.task.farmingmanagementsystem.model.Cultivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CultivationRepository extends JpaRepository<Cultivation, Long> {
    Optional<Cultivation> findByPlotNameOrPlotCropsOrFarmingDateOrTractorName(String plotName, String crops, Date date, String tractorName);
}
