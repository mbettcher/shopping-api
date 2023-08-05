package br.com.mtonon.shopping.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.shopping.domain.dto.ShopDTO;
import br.com.mtonon.shopping.domain.dto.ShopReportDTO;
import br.com.mtonon.shopping.services.ReportService;
import br.com.mtonon.shopping.services.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShopController {

	private final ShopService shopService;

	private final ReportService reportService;
	
	@GetMapping(value = "/shopping")
	public List<ShopDTO> getShops(){
		return this.shopService.getAll();
	}
	
	@GetMapping(value = "/shopping/shopByUser/{userIdentifier}")
	public List<ShopDTO> getShops(@PathVariable String userIdentifier){
		return this.shopService.getByUser(userIdentifier);
	}
	
	@GetMapping(value = "/shopping/shopByDate")
	public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO){
		return this.shopService.getByDate(shopDTO);
	}
	
	@GetMapping(value = "/shopping/{id}")
	public ShopDTO findById(@PathVariable Long id) {
		return this.shopService.findById(id);
	}
	
	@PostMapping(value = "/shopping")
	@ResponseStatus(HttpStatus.CREATED)
	public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO) {
		return this.shopService.save(shopDTO);
	}
	
	@GetMapping(value = "/shopping/search")
	public List<ShopDTO> getShopsByFilter(
			@RequestParam(name = "dataInicio", required = true)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
			@RequestParam(name = "dataFim", required = false)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
			@RequestParam(name = "valorMinimo", required = false) Float valorMinimo){
		return reportService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
	}
	
	@GetMapping(value = "/shopping/report")
	public ShopReportDTO getReportByDate(
			@RequestParam(name = "dataInicio", required = true)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
			@RequestParam(name = "dataFim", required = true)
			@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {
		return reportService.getReportByDate(dataInicio, dataFim);
	}
}
