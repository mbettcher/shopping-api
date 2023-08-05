package br.com.mtonon.shopping.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mtonon.shopping.converter.DTOConverter;
import br.com.mtonon.shopping.domain.Shop;
import br.com.mtonon.shopping.domain.dto.ItemDTO;
import br.com.mtonon.shopping.domain.dto.ProductDTO;
import br.com.mtonon.shopping.domain.dto.ShopDTO;
import br.com.mtonon.shopping.repositories.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	public List<ShopDTO> getAll(){
		List<Shop> shops = this.shopRepository.findAll();
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByUser(String userIdentifier){
		List<Shop> shops = this.shopRepository.findAllByUserIdentifier(userIdentifier);
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<Shop> shops = this.shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());
	}
	
	public ShopDTO findById(long ProductId) {
		Optional<Shop> shop = this.shopRepository.findById(ProductId);
		if(shop.isPresent()) {
			return DTOConverter.convert(shop.get());
		}
		return null;
	}
	
	public ShopDTO save(ShopDTO shopDTO) {
		
		if(userService.getUserByCpf(shopDTO.getUserIdentifier()) == null) {
			return null;
		}
		
		if(!validateProducts(shopDTO.getItems())) {
			return null;
		}
		
		shopDTO.setTotal(shopDTO
				.getItems()
				.stream()
				.map(x -> x.getPrice())
				.reduce((float) 0, Float::sum));
		
		Shop shop = Shop.convert(shopDTO);
		shop.setDate(LocalDateTime.now());
		
		shop = this.shopRepository.save(shop);
		return DTOConverter.convert(shop);
		
	}
	
	private boolean validateProducts(List<ItemDTO> items) {
		for(ItemDTO item : items) {
			ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
			if(productDTO == null) {
				return false;
			}
			item.setPrice(productDTO.getPreco());
		}
		return true;
	}

}
