package br.com.tarefas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.tarefas.controller.response.ErroResponse;

@ControllerAdvice
public class CustomGloablExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		List<ErroResponse> errors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(x -> new ErroResponse (x.getField(), x.getDefaultMessage()))
			.collect(Collectors.toList());
		
		return ResponseEntity.badRequest().body(errors);
	}

}
