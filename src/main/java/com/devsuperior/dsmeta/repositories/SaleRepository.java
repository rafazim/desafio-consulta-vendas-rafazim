package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SummaryMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND (:name IS NULL OR UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')))",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND (:name IS NULL OR UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')))")
    Page<Sale> searchByReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SummaryMinDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj WHERE obj.date BETWEEN :minDate AND :maxDate GROUP BY obj.seller.name")
    List<SummaryMinDTO> searchSummary(LocalDate minDate, LocalDate maxDate);
}
