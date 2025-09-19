package br.com.arthur.api.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DadosInvalidosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}