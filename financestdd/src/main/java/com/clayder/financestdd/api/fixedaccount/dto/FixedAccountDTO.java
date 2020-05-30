package com.clayder.financestdd.api.fixedaccount.dto;

import lombok.*;

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
