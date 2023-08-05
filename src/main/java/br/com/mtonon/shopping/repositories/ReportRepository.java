package br.com.mtonon.shopping.repositories;

import java.time.LocalDate;
import java.util.List;

import br.com.mtonon.shopping.domain.Shop;
import br.com.mtonon.shopping.domain.dto.ShopReportDTO;

public interface ReportRepository {

	public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo);
	
	public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim);
}
