package br.com.mtonon.shopping.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.mtonon.shopping.domain.dto.ErrorDTO;
import br.com.mtonon.shopping.exception.ProductNotFoundException;
import br.com.mtonon.shopping.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "br.com.mtonon.shopping.resources")
public class ShoppingControllerAdvice {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorDTO handlerProductNotFound(ProductNotFoundException productNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Produto não localizado!");
		errorDTO.setTimestamp(LocalDateTime.now());
		return errorDTO;
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public ErrorDTO handlerUserNotFound(UserNotFoundException userNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Usuário não localizado!");
		errorDTO.setTimestamp(LocalDateTime.now());
		return errorDTO;
	}

}
