package com.clayder.financestdd.api.fixedaccount.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedAccountDTO {

	private Integer id;
	private String name;
	private double price;
	private Integer paymentDay;
	private String owner;

}
