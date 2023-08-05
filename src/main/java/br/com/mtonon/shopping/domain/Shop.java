package br.com.mtonon.shopping.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.mtonon.shopping.domain.dto.ShopDTO;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop", schema = "shoppings" )
public class Shop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_identifier")
	private String userIdentifier;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
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
