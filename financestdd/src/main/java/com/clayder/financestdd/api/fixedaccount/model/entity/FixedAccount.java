package com.clayder.financestdd.api.fixedaccount.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FixedAccount {
    private Integer id;
    private String name;
    private double price;
    private Integer paymentDay;
    private String owner;
}
