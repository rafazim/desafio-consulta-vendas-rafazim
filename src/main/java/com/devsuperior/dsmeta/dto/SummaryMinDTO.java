package com.devsuperior.dsmeta.dto;

public class SummaryMinDTO {

    private String sellerName;
    private Double total;

    public SummaryMinDTO(String name, Double total) {
        this.sellerName = name;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
