package br.com.mtonon.shopping.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mtonon.shopping.domain.dto.ShopDTO;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "shop")
public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userIdentifier;
	private float total;
	private LocalDateTime date;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Item> items;
	
	public static Shop convert(ShopDTO shopDTO) {
		Shop shop = new Shop();
		shop.setUserIdentifier(shopDTO.getUserIdentifier());
		shop.setTotal(shopDTO.getTotal());
		shop.setDate(shopDTO.getDate());
		shop.setItems(shopDTO.getItems().stream().map(Item::convert).collect(Collectors.toList()));
		return shop;
	}

}
