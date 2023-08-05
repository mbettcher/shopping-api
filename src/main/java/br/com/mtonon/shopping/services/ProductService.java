package br.com.mtonon.shopping.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.mtonon.shopping.domain.dto.ProductDTO;
import br.com.mtonon.shopping.exception.ProductNotFoundException;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private static String userApiURL = "http://localhost:8081";

	public ProductDTO getProductByIdentifier(String productIdentifier) {

		try {

			WebClient webClient = WebClient.builder().baseUrl(userApiURL).build();

			Mono<ProductDTO> product = webClient.get().uri("/product/" + productIdentifier).retrieve()
					.bodyToMono(ProductDTO.class);

			return product.block();

		} catch (Exception e) {

			throw new ProductNotFoundException();

		}
	}

}
