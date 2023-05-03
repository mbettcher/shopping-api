package br.com.mtonon.shopping.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.shopping.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>{
	
	public List<Shop> findAllByUserIdentifier(String userIdentifier);
	
	public List<Shop> findAllByTotalGreaterThan(float total);
	
	public List<Shop> findAllByDateGreaterThan(LocalDateTime date);

}
