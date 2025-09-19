package br.com.arthur.api.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex) {
        Map<String, String> erroDetalhes = new HashMap<>();
        erroDetalhes.put("erro", "Recurso Não Encontrado");
        erroDetalhes.put("mensagem", ex.getMessage());
        return new ResponseEntity<>(erroDetalhes, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<Object> handleDadosInvalidosException(DadosInvalidosException ex) {
        Map<String, String> erroDetalhes = new HashMap<>();
        erroDetalhes.put("erro", "Dados Inválidos");
        erroDetalhes.put("mensagem", ex.getMessage());
        return new ResponseEntity<>(erroDetalhes, HttpStatus.BAD_REQUEST);
    }
}
