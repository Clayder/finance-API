package com.clayder.financestdd.api.fixedaccount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedAccountDTO {

    private Long id;

    @NotEmpty
    @Length(min=2, max=50)
    private String name;

    @NotNull
    @DecimalMin(value = "0.1", inclusive = true)
    private double price;

    @NotNull
    @Min(1)
	@Max(31)
    private Integer paymentDay;

    @NotEmpty
    @Length(min=5, max=50)
    private String owner;

}
