package br.com.mtonon.shopping.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.mtonon.shopping.domain.dto.UserDTO;
import br.com.mtonon.shopping.exception.UserNotFoundException;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	private static String userApiURL = "http://localhost:8080";
	
	public UserDTO getUserByCpf(String cpf) {
		
		try {
			
			WebClient webClient = WebClient.builder().baseUrl(userApiURL).build();
			
			Mono<UserDTO> user = webClient.get().uri("/user/"+ cpf + "/cpf").retrieve().bodyToMono(UserDTO.class);
			
			return user.block();
			
		}catch(Exception e) {
			
			throw new UserNotFoundException();
			
		}
	}

}
