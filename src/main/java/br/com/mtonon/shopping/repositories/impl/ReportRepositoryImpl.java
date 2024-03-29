package br.com.mtonon.shopping.repositories.impl;

import java.time.LocalDate;
import java.util.List;

import br.com.mtonon.shopping.domain.Shop;
import br.com.mtonon.shopping.domain.dto.ShopReportDTO;
import br.com.mtonon.shopping.repositories.ReportRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ReportRepositoryImpl implements ReportRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT s ");
		sb.append(" FROM Shop s ");
		sb.append(" WHERE s.date >= :dataInicio ");
		
		if(dataFim != null) {
			sb.append(" AND s.date <= :dataFim ");
		}
		
		if(valorMinimo != null) {
			sb.append(" AND s.total >= :valorMinimo ");
		}
		
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("dataInicio", dataInicio.atTime(0,0));
		
		if(dataFim != null) {
			query.setParameter("dataFim", dataFim.atTime(23,59));
		}
		
		if(valorMinimo != null) {
			query.setParameter("valorMinimo", valorMinimo);
		}

		return query.getResultList();
	}

	@Override
	public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {

		StringBuilder sb = new StringBuilder();
		sb.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
		sb.append("from shoppings.shop sp ");
		sb.append("where sp.date >= :dataInicio ");
		sb.append("and sp.date <= :dataFim ");
		
		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter("dataInicio", dataInicio.atTime(0,0));
		query.setParameter("dataFim", dataFim.atTime(23,59));
		Object[] result = (Object[]) query.getSingleResult();
		
		ShopReportDTO shopReportDTO = new ShopReportDTO();
		shopReportDTO.setCount(Long.valueOf(result[0].toString()));
		shopReportDTO.setTotal((Double) result[1]);
		shopReportDTO.setMean((Double) result[2]);
		return shopReportDTO;
	}

}
