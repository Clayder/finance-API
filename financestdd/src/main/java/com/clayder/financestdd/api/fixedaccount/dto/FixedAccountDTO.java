package com.clayder.financestdd.api.fixedaccount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedAccountDTO {

    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private double price;

    @NotNull
    private Integer paymentDay;

    @NotEmpty
    private String owner;

}
