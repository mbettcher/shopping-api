package br.com.mtonon.shopping.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.mtonon.shopping.domain.Shop;
import br.com.mtonon.shopping.domain.dto.ShopDTO;
import br.com.mtonon.shopping.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	
	private final ShopRepository shopRepository;
	
	public List<ShopDTO> getAll(){
		List<Shop> shops = this.shopRepository.findAll();
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByUser(String userIdentifier){
		List<Shop> shops = this.shopRepository.findAllByUserIdentifier(userIdentifier);
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}
	
	public List<ShopDTO> getByDate(ShopDTO shopDTO) {
		List<Shop> shops = this.shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
		return shops.stream().map(ShopDTO::convert).collect(Collectors.toList());
	}
	
	public ShopDTO findById(long ProductId) {
		Optional<Shop> shop = this.shopRepository.findById(ProductId);
		if(shop.isPresent()) {
			return ShopDTO.convert(shop.get());
		}
		return null;
	}
	
	public ShopDTO save(ShopDTO shopDTO) {
		shopDTO.setTotal(shopDTO.getItems().stream().map(x -> x.getPrice()).reduce((float) 0, Float::sum));
		Shop shop = Shop.convert(shopDTO);
		shop.setDate(LocalDateTime.now());
		shop = this.shopRepository.save(shop);
		return ShopDTO.convert(shop);
	}

}
