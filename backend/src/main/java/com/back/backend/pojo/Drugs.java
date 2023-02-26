package com.back.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drugs {
    private Integer id;

    private String name;

    private Double price;

    private Double purchasePrice;

    private Long num;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date introduceDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date qualityPeriod;

    private String supplyUnit;

    private String productUnit;
}
