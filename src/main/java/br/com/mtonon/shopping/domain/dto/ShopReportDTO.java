package br.com.mtonon.shopping.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopReportDTO {
	
	private Long count;
	private Double total;
	private Double mean;

}
