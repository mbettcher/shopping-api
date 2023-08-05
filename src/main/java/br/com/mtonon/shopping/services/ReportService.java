package br.com.mtonon.shopping.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.mtonon.shopping.converter.DTOConverter;
import br.com.mtonon.shopping.domain.Shop;
import br.com.mtonon.shopping.domain.dto.ShopDTO;
import br.com.mtonon.shopping.domain.dto.ShopReportDTO;
import br.com.mtonon.shopping.repositories.ReportRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepository reportRepository;

	public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
		List<Shop> shops = reportRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}
	
	public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
		return reportRepository.getReportByDate(dataInicio, dataFim);
	}

}
