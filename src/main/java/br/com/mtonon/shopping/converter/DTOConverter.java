package br.com.mtonon.shopping.converter;

import java.util.stream.Collectors;

import br.com.mtonon.shopping.domain.Item;
import br.com.mtonon.shopping.domain.Shop;
import br.com.mtonon.shopping.domain.dto.ItemDTO;
import br.com.mtonon.shopping.domain.dto.ShopDTO;

public class DTOConverter {
	
	public static ItemDTO convert(Item item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setProductIdentifier(item.getProductIdentifier());
		itemDTO.setPrice(item.getPrice());
		return itemDTO;
	}
	
	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setId(shop.getId());
		shopDTO.setUserIdentifier(shop.getUserIdentifier());
		shopDTO.setDate(shop.getDate());
		shopDTO.setTotal(shop.getTotal());
		shopDTO.setItems(shop.getItems().stream().map(DTOConverter::convert).collect(Collectors.toList()));
		return shopDTO;
	}

}
