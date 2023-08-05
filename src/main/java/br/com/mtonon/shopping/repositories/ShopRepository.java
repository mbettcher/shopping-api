package br.com.mtonon.shopping.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.shopping.domain.Shop;
@Primary
public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository{
	
	public List<Shop> findAllByUserIdentifier(String userIdentifier);
	
	public List<Shop> findAllByTotalGreaterThan(float total);
	
	public List<Shop> findAllByDateGreaterThan(LocalDateTime date);

}
