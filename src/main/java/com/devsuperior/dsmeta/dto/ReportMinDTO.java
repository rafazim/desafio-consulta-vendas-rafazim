package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class ReportMinDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public ReportMinDTO(Long id, LocalDate date, Double amount, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = name;
    }

    public ReportMinDTO(Sale sale){
        id = sale.getId();
        date = sale.getDate();
        amount = sale.getAmount();
        sellerName = sale.getSeller().getName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
